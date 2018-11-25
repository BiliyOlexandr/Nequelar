package com.ommyf.nequelar.categories.presentation;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.ommyf.nequelar.NequelerApp;
import com.ommyf.nequelar.R;
import com.ommyf.nequelar.categories.data.Category;
import com.ommyf.nequelar.databinding.ActivityCategoriesBinding;
import com.ommyf.nequelar.databinding.ViewCategoryItemBinding;
import com.ommyf.nequelar.products.presentation.ProductsActivity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class CategoriesActivity extends AppCompatActivity
    implements CategoriesViewCallbacks, CategoriesRouter {

  private ActivityCategoriesBinding mViewBinding;
  private CategoriesAdapter mAdapter;
  @Inject CategoriesPresenter mCategoriesPresenter;

  public static void start(Context context) {
    context.startActivity(new Intent(context, CategoriesActivity.class));
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((NequelerApp) getApplicationContext()).getComponent().inject(this);
    mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_categories);

    // Setup home button
    ActionBar mActionBar = getSupportActionBar();
    if (mActionBar != null) {
      mActionBar.setHomeButtonEnabled(true);
      mActionBar.setDisplayHomeAsUpEnabled(true);
    }

    // Setup list
    mAdapter = new CategoriesAdapter(this);
    mViewBinding.categoriesRecycler.setLayoutManager(new LinearLayoutManager(this));
    mViewBinding.categoriesRecycler.setAdapter(mAdapter);
    // FetchData
    mCategoriesPresenter.fetchCategories(0);

    mViewBinding.categoriesRecycler.requestFocus();
  }

  @Override protected void onStart() {
    super.onStart();
    if (mCategoriesPresenter != null) {
      mCategoriesPresenter.takeRouter(this);
      mCategoriesPresenter.takeView(this);
    }
  }

  @Override protected void onStop() {
    if (mCategoriesPresenter != null) {
      mCategoriesPresenter.dropRouter(this);
      mCategoriesPresenter.dropView(this);
    }
    super.onStop();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return false;
  }

  @Override public void onNewCategory(Category category) {
    mAdapter.addCategory(category);
  }

  @Override public void onError(Throwable e) {
    Toast.makeText(this, "Oh! Something went wrong", Toast.LENGTH_LONG).show();
  }

  @Override public void navigateToProducts(int categoryId) {
    ProductsActivity.start(this, categoryId);
  }

  static class CategoriesAdapter
      extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    private CategoriesRouter mRouter;
    private List<Category> mCategories;

    CategoriesAdapter(CategoriesRouter router) {
      mRouter = router;
      mCategories = new ArrayList<>();
    }

    void addCategory(Category newCategory) {
      mCategories.add(newCategory);
      notifyItemInserted(mCategories.indexOf(newCategory));
    }

    @Override public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.view_category_item, parent, false);
      return new CategoryViewHolder(view);
    }

    @Override public void onBindViewHolder(CategoryViewHolder holder, int position) {
      Category currentCategory = mCategories.get(position);
      // Fill image
      Picasso.with(holder.itemView.getContext())
          .load(currentCategory.getImgUri())
          .into(holder.mItemBinding.image);

      // Fill texts
      holder.mItemBinding.title.setText(currentCategory.getName());
      holder.mItemBinding.description.setText(currentCategory.getDescription());

      holder.itemView.setOnClickListener(v -> {
        if (mRouter != null) {
          mRouter.navigateToProducts(currentCategory.getId());
        }
      });
    }

    @Override public int getItemCount() {
      return mCategories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

      ViewCategoryItemBinding mItemBinding;

      CategoryViewHolder(View itemView) {
        super(itemView);
        mItemBinding = ViewCategoryItemBinding.bind(itemView);
      }
    }
  }
}
