package com.patryk.springer.cashmanager.view.shoppinglists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patryk.springer.cashmanager.R
import kotlinx.android.synthetic.main.row_shopping_list.view.*

/**
 * Created by Patryk Springer on 2019-06-15.
 */
class ShoppingListAdapter(private val mPresenter: BaseListContract.Presenter) :
    RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_shopping_list, parent, false)
        val viewHolder = ViewHolder(view)
        view.setOnLongClickListener {
            mPresenter.onShoppingListLongClicked(viewHolder.adapterPosition)
            true
        }
        view.setOnClickListener {
            mPresenter.onShoppingListClicked(viewHolder.adapterPosition)
        }
        return viewHolder
    }

    override fun getItemCount(): Int = mPresenter.getShoppingListSize()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mPresenter.onShoppingListBind(position, holder)
    }

    class ViewHolder(val mItemView: View) : RecyclerView.ViewHolder(mItemView),
        BaseListContract.RowView {

        override fun showListName(name: String) = with(mItemView) {
            tv_row_shopping_list_name.text = name
        }

        override fun showProductsCount(uncheckedCount: Int, totalCount: Int) = with(mItemView) {
            tv_row_shopping_list_total_prod.text = totalCount.toString()
            tv_row_shopping_list_unchecked_prod.text = uncheckedCount.toString()
        }

        override fun showCreatedDate(date: String) = with(mItemView) {
            tv_row_shopping_list_created_date.text = date
        }
    }
}