package com.ommyf.nequelar.shops;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.ommyf.nequelar.NequelerApp;
import com.ommyf.nequelar.R;
import com.ommyf.nequelar.databinding.ViewShopItemBinding;
import com.ommyf.nequelar.shops.data.Shop;
import com.ommyf.nequelar.shops.domain.GetShopsInteractor;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ShopsActivity extends AppCompatActivity {

  private RecyclerView mRecycler;
  private ShopsAdapter mAdapter;
  @Inject GetShopsInteractor mGetShopInteractor;

  public static void start(Context context) {
    context.startActivity(new Intent(context, ShopsActivity.class));
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shops);
    ((NequelerApp) getApplicationContext()).getComponent().inject(this);
    // Setup home button
    ActionBar mActionBar = getSupportActionBar();
    if (mActionBar != null) {
      mActionBar.setHomeButtonEnabled(true);
      mActionBar.setDisplayHomeAsUpEnabled(true);
    }

    mAdapter = new ShopsAdapter();
    mRecycler = ((RecyclerView) findViewById(R.id.shops_recycler));
    mRecycler.setLayoutManager(new GridLayoutManager(this, 2));
    mRecycler.setAdapter(mAdapter);

    mGetShopInteractor.execute(shops -> mAdapter.setShops(shops), Throwable::printStackTrace, 0);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return false;
  }

  static class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.ShopViewHolder> {

    private List<Shop> mShops;

    ShopsAdapter() {
      mShops = new ArrayList<>();
    }

    void addShop(Shop newShop) {
      mShops.add(newShop);
      notifyItemInserted(mShops.indexOf(newShop));
    }

    void setShops(List<Shop> shops) {
      mShops = shops;
      notifyDataSetChanged();
    }

    @Override public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view =
          LayoutInflater.from(parent.getContext()).inflate(R.layout.view_shop_item, parent, false);
      return new ShopViewHolder(view);
    }

    @Override public void onBindViewHolder(ShopViewHolder holder, int position) {
      Shop currentShop = mShops.get(position);
      // Fill image
      Picasso.with(holder.itemView.getContext())
          .load(currentShop.getImgUri())
          .into(holder.mItemBinding.shopImage);

      // Fill texts
      holder.mItemBinding.title.setText(currentShop.getName());
      holder.mItemBinding.description.setText(currentShop.getDescription());
      //holder.itemView.setOnClickListener();
    }

    @Override public int getItemCount() {
      return mShops.size();
    }

    class ShopViewHolder extends RecyclerView.ViewHolder {

      ViewShopItemBinding mItemBinding;

      ShopViewHolder(View itemView) {
        super(itemView);
        mItemBinding = ViewShopItemBinding.bind(itemView);
      }
    }
  }
}
