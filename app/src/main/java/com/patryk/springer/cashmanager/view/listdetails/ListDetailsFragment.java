package com.patryk.springer.cashmanager.view.listdetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.patryk.springer.cashmanager.R;
import com.patryk.springer.cashmanager.view.base.BaseFragment;
import com.patryk.springer.cashmanager.view.main.MainActivity;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

/**
 * Created by Patryk Springer on 2019-06-17.
 */
public class ListDetailsFragment extends BaseFragment<ListDetailsContract.Presenter>
        implements ListDetailsContract.View, ActionMode.Callback {

    static final int NEW_PRODUCT_INTENT_CODE = 8162;
    static final int EDIT_PRODUCT_INTENT_CODE = 9351;
    static final String PRODUCT_NAME_KEY = "productName";
    static final String PRODUCT_QUANTITY_KEY = "quantity";
    private static final String LIST_ID_KEY = "listId";
    @Inject
    ListDetailsContract.Presenter mPresenter;
    private FloatingActionButton mFabAddProduct;
    private ProductsListAdapter mAdapter;
    private ActionMode mActionMode = null;

    public static ListDetailsFragment newInstance(int listId) {
        ListDetailsFragment fragment = new ListDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(LIST_ID_KEY, listId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_shopping_details;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvShoppingList = view.findViewById(R.id.rv_shopping_details_list);
        mFabAddProduct = view.findViewById(R.id.fab_shopping_details_add_product);
        mAdapter = new ProductsListAdapter(mPresenter);
        rvShoppingList.setAdapter(mAdapter);
        rvShoppingList.setLayoutManager(new LinearLayoutManager(getContext()));

        mFabAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNewProductDialog();
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null) {
            int listId = bundle.getInt(LIST_ID_KEY);
            mPresenter.refreshView(listId);
        }
    }

    private void showNewProductDialog() {
        NewProductDialogFragment dialog = new NewProductDialogFragment();
        dialog.setTargetFragment(this, NEW_PRODUCT_INTENT_CODE);
        dialog.show(getFragmentManager(), null);
    }


    @Override
    public void showListName(@NotNull String name, boolean isArchived) {
        String listName = name;
        if (isArchived) {
            listName += " [" + getString(R.string.archived_list) + "]";
        }
        ((MainActivity) getMBaseActivity()).setToolbarTitle(listName);
    }

    @Override
    public void showAddNewProductButton(boolean isVisible) {
        if (isVisible) {
            mFabAddProduct.show();
        } else {
            mFabAddProduct.hide();
        }
    }

    @Override
    public void refreshList() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showActionMenu() {
        mActionMode = ((MainActivity) getMBaseActivity()).showActionMenu(this);
    }

    @Override
    public void showEditProductDialog(@NotNull String name, int quantity) {
        NewProductDialogFragment dialog =
                NewProductDialogFragment.Companion.newInstance(name, quantity);
        dialog.setTargetFragment(this, EDIT_PRODUCT_INTENT_CODE);
        dialog.show(getFragmentManager(), null);
    }

    @NotNull
    @Override
    public ListDetailsContract.Presenter getMPresenter() {
        return mPresenter;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.product_action_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_delete) {
            mPresenter.onProductRemoved();
            mode.finish();
            return true;
        } else if (itemId == R.id.menu_edit) {
            mPresenter.onProductEditClicked();
            mode.finish();
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mActionMode = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            String name = data.getStringExtra(PRODUCT_NAME_KEY);
            int quantity = data.getIntExtra(PRODUCT_QUANTITY_KEY, -1);
            handleProductIntent(name, quantity, requestCode);
        }
    }

    private void handleProductIntent(String name, int quantity, int requestCode) {
        if (requestCode == EDIT_PRODUCT_INTENT_CODE) {
            mPresenter.onProductEditConfirmed(name, quantity);
        } else if (requestCode == NEW_PRODUCT_INTENT_CODE) {
            mPresenter.onNewProductAdded(name, quantity);
        }
    }
}
