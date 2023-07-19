    package com.example.asmnetwork.adapter;

    import android.annotation.SuppressLint;
    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import java.util.List;

    import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.GioHangItem;
    import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
    import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.GioHangDAO;

    public class CartAdapter extends  RecyclerView.Adapter<CartAdapter.ViewHolder>{
        private Context context;
        private List<GioHangItem> gioHangItems;

        public CartAdapter(Context context, List<GioHangItem> gioHangItems) {
            this.context = context;
            this.gioHangItems = gioHangItems;
        }



        public void updateCartItems(List<GioHangItem> gioHangItems) {
            this.gioHangItems = gioHangItems;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            if (gioHangItems == null || gioHangItems.size() <= position) {
                return;
            }
            GioHangItem gioHangItem = gioHangItems.get(position);
            holder.tvTenSanPham.setText(gioHangItem.getTenSanPham());
            holder.tvGiaSanPham.setText(String.valueOf(gioHangItem.getGiaSanPham()));
            byte[] image = gioHangItem.getImageSanPham();
//            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
//            holder.imgSanPham.setImageBitmap(bitmap);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Xử lý khi người dùng bấm vào mục trong giỏ hàng
                    Toast.makeText(context, "Bạn đã bấm vào sản phẩm ", Toast.LENGTH_SHORT).show();
                }
            });

            holder.btnXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GioHangDAO gioHangDAO = new GioHangDAO(context);
                    gioHangDAO.deleteProductFromCart(gioHangItem.getId());
                    gioHangItems.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Sản phẩm đã được xóa khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return gioHangItems.size();
        }



        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvTenSanPham;
            TextView tvGiaSanPham;
            ImageView imgSanPham;
            ImageView btnXoa;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTenSanPham = itemView.findViewById(R.id.productName);
                tvGiaSanPham = itemView.findViewById(R.id.productPrice);
                imgSanPham = itemView.findViewById(R.id.productImage);
                btnXoa = itemView.findViewById(R.id.btnDelete);
            }
        }
    }
