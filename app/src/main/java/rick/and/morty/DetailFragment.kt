package rick.and.morty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import rick.and.morty.data.datasource.dto.Character
import rick.and.morty.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val args : DetailFragmentArgs by navArgs()
    private lateinit var character : Character
    private lateinit var binding : FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        character = args.character
        binding.character = character
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

}