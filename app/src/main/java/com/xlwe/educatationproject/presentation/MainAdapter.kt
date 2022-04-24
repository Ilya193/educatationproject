package com.xlwe.educatationproject.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xlwe.educatationproject.R

class MainAdapter(
    private val retry: Retry,
    private val collapseListener: CollapseListener
) : ListAdapter<BookUi, MainAdapter.MainViewHolder>(wordsComparator) {

    //private val books = ArrayList<BookUi>()

    /*fun update(new: List<BookUi>) {
        books.clear()
        books.addAll(new)
        notifyDataSetChanged()
    }*/

    abstract class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(book: BookUi) {}

        class FullscreenProgress(view: View) : MainViewHolder(view)

        abstract class Info(view: View) : MainViewHolder(view) {
            private val name = itemView.findViewById<TextView>(R.id.textView)

            override fun bind(book: BookUi) {
                book.map(object : BookUi.StringMapper {
                    override fun map(text: String) {
                        name.text = text
                    }
                })
            }
        }

        class Base(view: View) : Info(view)

        class Testament(view: View, private val collapse: CollapseListener) : Info(view) {
            private val collapseView = itemView.findViewById<ImageView>(R.id.collapseView)

            override fun bind(book: BookUi) {
                super.bind(book)
                itemView.setOnClickListener {
                    book.collapseOrExpand(collapse)
                }

                book.showCollapsed(object : BookUi.CollapseMapper {
                    override fun show(collapsed: Boolean) {
                        val iconId = if (collapsed) {
                            R.drawable.ic_expand_more
                        } else {
                            R.drawable.ic_expand_less
                        }

                        collapseView.setImageResource(iconId)
                    }
                })
            }
        }

        class Fail(view: View, private val retry: Retry) : MainViewHolder(view) {
            private val message = itemView.findViewById<TextView>(R.id.messageTextView)
            private val btn = itemView.findViewById<Button>(R.id.tryAgainButton)

            override fun bind(book: BookUi) {
                book.map(object : BookUi.StringMapper{
                    override fun map(text: String) {
                        message.text = text
                    }
                })

                btn.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is BookUi.Base -> 0
            is BookUi.Fail -> 1
            is BookUi.Progress -> 2
            is BookUi.Testament -> 3
            else -> -1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val viewHolder = when (viewType) {
            0 -> MainViewHolder.Base(R.layout.book_layout.makeView(parent))
            1 -> MainViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
            2 -> MainViewHolder.FullscreenProgress(R.layout.progress_fullscreen.makeView(parent))
            else -> MainViewHolder.Testament(R.layout.testament.makeView(parent), collapseListener)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface Retry {
        fun tryAgain()
    }

    interface CollapseListener {
        fun collapseOrExpand(id: Int)
    }

    private companion object {
        val wordsComparator = object : DiffUtil.ItemCallback<BookUi>() {
            override fun areItemsTheSame(oldItem: BookUi, newItem: BookUi): Boolean {
                return oldItem.same(newItem)
            }

            override fun areContentsTheSame(oldItem: BookUi, newItem: BookUi): Boolean {
                return oldItem.sameContent(newItem)
            }
        }
    }
}

private fun Int.makeView(parent: ViewGroup) = LayoutInflater.from(parent.context).inflate(
    this,
    parent,
    false
)