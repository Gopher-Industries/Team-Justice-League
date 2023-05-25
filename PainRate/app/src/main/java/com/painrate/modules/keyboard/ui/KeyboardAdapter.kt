package com.painrate.modules.keyboard.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.painrate.R
import com.painrate.databinding.RowKeyboardBinding
import com.painrate.modules.keyboard.`data`.model.KeyboardRowModel
import kotlin.Int
import kotlin.collections.List

class KeyboardAdapter(
  var list: List<KeyboardRowModel>
) : RecyclerView.Adapter<KeyboardAdapter.RowKeyboardVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowKeyboardVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_keyboard,parent,false)
    return RowKeyboardVH(view)
  }

  override fun onBindViewHolder(holder: RowKeyboardVH, position: Int) {
    val keyboardRowModel = KeyboardRowModel()
    // TODO uncomment following line after integration with data source
    // val keyboardRowModel = list[position]
    holder.binding.keyboardRowModel = keyboardRowModel
  }

  override fun getItemCount(): Int = 3
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<KeyboardRowModel>) {
    list = newData
    notifyDataSetChanged()
  }

  fun setOnItemClickListener(clickListener: OnItemClickListener) {
    this.clickListener = clickListener
  }

  interface OnItemClickListener {
    fun onItemClick(
      view: View,
      position: Int,
      item: KeyboardRowModel
    ) {
    }
  }

  inner class RowKeyboardVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowKeyboardBinding = RowKeyboardBinding.bind(itemView)
  }
}
