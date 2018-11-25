package com.ommyf.nequelar.products.presentation;

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
import com.ommyf.nequelar.NequelerApp;
import com.ommyf.nequelar.R;
import com.ommyf.nequelar.databinding.ActivityProductsBinding;
import com.ommyf.nequelar.databinding.ViewProductItemBinding;
import com.ommyf.nequelar.product.ProductActivity;
import com.ommyf.nequelar.product.data.Product;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ProductsActivity extends AppCompatActivity
    implements ProductsViewCallbacks, ProductsRouter {

  public static final String CATEGORY_ID = "category_id";

  private ActivityProductsBinding mViewBinding;
  private ProductsAdapter mAdapter;
  @Inject ProductsPresenter mProductsPresenter;

  public static void start(Context context, int categoriesId) {
    Intent intent = new Intent(context, ProductsActivity.class);
    intent.putExtra(CATEGORY_ID, categoriesId);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((NequelerApp) getApplicationContext()).getComponent().inject(this);
    mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_products);

    // Setup home button
    ActionBar mActionBar = getSupportActionBar();
    if (mActionBar != null) {
      mActionBar.setHomeButtonEnabled(true);
      mActionBar.setDisplayHomeAsUpEnabled(true);
    }

    // Setup list
    mAdapter = new ProductsAdapter();
    mViewBinding.productsRecycler.setLayoutManager(new LinearLayoutManager(this));
    mViewBinding.productsRecycler.setAdapter(mAdapter);
    // FetchData
    mProductsPresenter.fetchProducts(getIntent().getIntExtra(CATEGORY_ID, -1));

    mViewBinding.productsRecycler.requestFocus();
  }

  @Override protected void onStart() {
    super.onStart();
    if (mProductsPresenter != null) {
      mProductsPresenter.takeRouter(this);
      mProductsPresenter.takeView(this);
    }
  }

  @Override protected void onStop() {
    if (mProductsPresenter != null) {
      mProductsPresenter.dropRouter(this);
      mProductsPresenter.dropView(this);
    }
    super.onStop();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return false;
  }

  @Override public void navigateToProduct(int productId) {

  }

  @Override public void onNewProduct(Product product) {
    mAdapter.addProduct(product);
  }

  @Override public void onError(Throwable e) {

  }

  static class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private List<Product> mProducts;

    ProductsAdapter() {
      mProducts = new ArrayList<>();
    }

    void addProduct(Product newProduct) {
      mProducts.add(newProduct);
      notifyItemInserted(mProducts.indexOf(newProduct));
    }

    @Override
    public ProductsAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.view_product_item, parent, false);
      return new ProductViewHolder(view);
    }

    @Override public void onBindViewHolder(ProductViewHolder holder, int position) {
      Product currentProduct = mProducts.get(position);
      // Fill image
      Picasso.with(holder.itemView.getContext())
          .load(currentProduct.getImgUri())
          .into(holder.mItemBinding.image);

      // Fill texts
      holder.mItemBinding.title.setText(currentProduct.getName());
      holder.mItemBinding.description.setText(currentProduct.getDescription());
      holder.mItemBinding.price.setText("$" + currentProduct.getPrice());
      holder.itemView.setOnClickListener(
          v -> ProductActivity.start(holder.itemView.getContext(), currentProduct));
    }

    @Override public int getItemCount() {
      return mProducts.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

      ViewProductItemBinding mItemBinding;

      ProductViewHolder(View itemView) {
        super(itemView);
        mItemBinding = ViewProductItemBinding.bind(itemView);
      }
    }
  }
}
