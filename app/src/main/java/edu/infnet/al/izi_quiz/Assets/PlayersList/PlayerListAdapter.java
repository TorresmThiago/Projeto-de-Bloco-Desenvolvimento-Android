package edu.infnet.al.izi_quiz.Assets.PlayersList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.infnet.al.izi_quiz.Assets.FontChangeCrawler;
import edu.infnet.al.izi_quiz.R;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerListViewHolder> {

    public static PlayerListItemClick playerListItemClick;
    private final ArrayList<Player> playerArrayList;
    private Context context;

    public PlayerListAdapter(Context context, ArrayList<Player> playerArrayList, PlayerListItemClick playerListItemClick) {
        this.playerListItemClick = playerListItemClick;
        this.context = context;
        this.playerArrayList = playerArrayList;
    }


    @Override
    public PlayerListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.asset_itemlist, parent, false);

        FontChangeCrawler fontChanger = new FontChangeCrawler(context.getAssets(), "fonts/neutra_text_bold.OTF");
        fontChanger.replaceFonts((ViewGroup) itemView);

        return new PlayerListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayerListViewHolder holder, int position) {
        Player player = playerArrayList.get(position);
        holder.playerListName.setText(player.getName());
    }

    @Override
    public int getItemCount() {
        return playerArrayList.size();
    }

    protected class PlayerListViewHolder extends RecyclerView.ViewHolder {

        ImageView playerListBackground;
        TextView playerListName;

        public PlayerListViewHolder(final View itemView) {
            super(itemView);

            this.playerListBackground = itemView.findViewById(R.id.ItemListBackground);
            this.playerListName = itemView.findViewById(R.id.ItemListName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    playerListItemClick.onPlayerClick(playerArrayList.get(getLayoutPosition()));
                }
            });
        }

    }

}
