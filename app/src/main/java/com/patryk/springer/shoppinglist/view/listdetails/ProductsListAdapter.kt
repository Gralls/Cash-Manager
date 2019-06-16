package com.patryk.springer.shoppinglist.view.listdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patryk.springer.shoppinglist.R
import kotlinx.android.synthetic.main.row_checked_product.view.*

/**
 * Created by Patryk Springer on 2019-06-16.
 */
class ProductsListAdapter(private val mPresenter: ListDetailsContract.Presenter) :
		RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

	override fun getItemViewType(position: Int): Int {
		return mPresenter.getViewTypeOfProduct(position)?.viewType ?: -1
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = when (ProductTypeEnum.getProductTypeByViewType(viewType)) {
			ProductTypeEnum.CHECKED -> {
				LayoutInflater.from(parent.context)
						.inflate(R.layout.row_checked_product, parent, false)
			}
			ProductTypeEnum.UNCHECKED -> {
				LayoutInflater.from(parent.context)
						.inflate(R.layout.row_unchecked_product, parent, false)
			}
		}
		val viewHolder = ViewHolder(view)
		view.setOnClickListener {
			mPresenter.onProductChecked(viewHolder.adapterPosition)
		}
		return viewHolder
	}

	override fun getItemCount(): Int = mPresenter.getProductListSize()

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		mPresenter.onProductRowBinded(position, holder)
	}

	inner class ViewHolder(private val mItemView: View) : RecyclerView.ViewHolder(mItemView),
			ListDetailsContract.RowView {

		override fun setProductName(name: String) = with(mItemView) {
			tv_row_product_name.text = name

		}

		override fun setProductQuantity(quantity: Int) = with(mItemView) {
			tv_row_product_quantity.text = quantity.toString()
		}

		override fun setProductChecked(isChecked: Boolean) = with(mItemView) {
			tv_row_product_name.isChecked = isChecked
		}
	}
}