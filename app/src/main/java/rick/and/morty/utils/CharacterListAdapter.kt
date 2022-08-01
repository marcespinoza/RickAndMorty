package rick.and.morty.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikhaellopez.circularimageview.CircularImageView
import rick.and.morty.MasterFragmentDirections
import rick.and.morty.R
import rick.and.morty.data.datasource.dto.Character
import rick.and.morty.data.datasource.dto.Result

class CharacterListAdapter(private val context: Context, var itemlist:ArrayList<Character>):
    RecyclerView.Adapter<CharacterListAdapter.CharacterLisViewHolder>()
{

    inner class CharacterLisViewHolder(view: View):RecyclerView.ViewHolder(view){
        val characterName : TextView = view.findViewById(R.id.txtCharacterName)
        val thumbnail : CircularImageView = view.findViewById(R.id.imageCharacter)
        val characterCard : CardView = view.findViewById(R.id.characterCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterLisViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterLisViewHolder(view = view)
    }

    override fun onBindViewHolder(holder: CharacterLisViewHolder, position: Int) {
        val character = itemlist[position]
        holder.characterName.text = character.name
        val imageUrl = character.image
        Glide.with(context).load(imageUrl).fitCenter().into(holder.thumbnail)
        holder.characterCard.setOnClickListener {
            val directions = MasterFragmentDirections.actionMasterFragmentToDetailFragment2(character)
            it.findNavController().navigate(directions)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(characterList:ArrayList<Character>)
    {
        this.itemlist.addAll(characterList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }
}