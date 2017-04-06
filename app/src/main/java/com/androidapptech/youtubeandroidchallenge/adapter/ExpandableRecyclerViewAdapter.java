package com.androidapptech.youtubeandroidchallenge.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapptech.youtubeandroidchallenge.util.LogUtil;
import com.androidapptech.youtubeandroidchallenge.R;
import com.androidapptech.youtubeandroidchallenge.retrofit.YoutubeItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

/**
 * Created by Benjamin on 4/5/2017.
 */

public class ExpandableRecyclerViewAdapter extends ExpandableRecyclerAdapter<ExpandableRecyclerViewAdapter.YoutubeleListItem> {

    private static final int TYPE_ITEM = 1001;
    private List<YoutubeleListItem> list;
    private OnItemClickListener listener;

    //private List<YoutubeleListItem> list;
    public ExpandableRecyclerViewAdapter(Context context, List<YoutubeleListItem> list) {
        super(context);
        this.list = list;
        setItems(list);
    }

    public interface OnItemClickListener {
        //public void onItemClick(String textName, String textViewBrief);
        public void onItemClick(String... parameters);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return new HeaderViewHolder(inflate(R.layout.item_header, parent));
            case TYPE_ITEM:
            default:
                return new YoutubeItemViewHolder(inflate(R.layout.single_card, parent));
        }
    }

    @Override
    public void onBindViewHolder(ExpandableRecyclerAdapter.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                ((HeaderViewHolder) holder).bind(position);
                break;
            case TYPE_ITEM:
            default:
                ((YoutubeItemViewHolder) holder).bind(position);
                break;
        }
    }


    public static class YoutubeleListItem extends ExpandableRecyclerAdapter.ListItem {
        private String Text;
        private String Thumb;
        private String Link;

        public YoutubeleListItem(String group) {
            super(TYPE_HEADER);

            Text = group;
        }

        public YoutubeleListItem(String title, String link, String thumb) {
            super(TYPE_ITEM);
            YoutubeItem item = new YoutubeItem(title, link, thumb);
            Text = title;
            Thumb = thumb;
            Link = link;
        }
    }

    private class HeaderViewHolder extends ExpandableRecyclerAdapter.HeaderViewHolder {

        private TextView name;

        private HeaderViewHolder(View view) {

            super(view, (ImageView) view.findViewById(R.id.item_arrow));
            name = (TextView) view.findViewById(R.id.item_header_name);
        }

        public void bind(int position) {

            super.bind(position);
            name.setText(visibleItems.get(position).Text);
        }
    }

    private class YoutubeItemViewHolder extends ExpandableRecyclerAdapter.ViewHolder {

        private TextView title, url;
        private ImageView image;

        private YoutubeItemViewHolder(final View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.imageView);
            url = (TextView) view.findViewById(R.id.tv_url);
            title = (TextView) view.findViewById(R.id.textView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(title.getText().toString(), url.getText().toString());
                }
            });
        }

        private void bind(int position) {
            title.setText(visibleItems.get(position).Text);
            url.setText(visibleItems.get(position).Link);
            downloadPhoto(visibleItems.get(position).Thumb, image);
        }
    }


    private Target<GlideDrawable> downloadPhoto(final String userUrlAvatar, ImageView imageView) {
        return Glide.with(mContext)
                .load(userUrlAvatar)
                .crossFade(0)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(true)
                .placeholder(R.mipmap.ic_launcher_round)
                .fallback(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        LogUtil.d(userUrlAvatar + " " + e.getMessage());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);
    }
}
