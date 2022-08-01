package rick.and.morty

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rick.and.morty.data.datasource.dto.Character
import rick.and.morty.databinding.FragmentMasterBinding
import rick.and.morty.ui.CharacterViewModel
import rick.and.morty.utils.CharacterListAdapter

@AndroidEntryPoint
class MasterFragment : Fragment() {

    private lateinit var binding : FragmentMasterBinding
    private val viewModel : CharacterViewModel by viewModels()
    var valueRepeat = 3
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: CharacterListAdapter
    private lateinit var layoutManager : LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CharacterListAdapter(requireContext(), ArrayList())
        viewModel.getAllCharactersData()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMasterBinding.inflate(inflater, container, false)
        recyclerView()
        requireActivity().addMenuProvider(object  : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                showPopUp()
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    private fun recyclerView(){
        recyclerView = binding.characterRecycler
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        callAPI()
    }

    private fun callAPI(){
        CoroutineScope(Dispatchers.Main).launch {
            repeat(valueRepeat){
                viewModel._characterValue.collect {value ->
                    when{
                        value.isLoading->{
                            binding.progressCircular.visibility = View.VISIBLE
                        }
                        value.error.isNotBlank()->{
                            binding.progressCircular.visibility = View.GONE
                            valueRepeat = 0
                            Toast.makeText(context, value.error, Toast.LENGTH_LONG).show()
                        }
                        value.characterList.isNotEmpty()->{
                            binding.progressCircular.visibility = View.GONE
                            valueRepeat = 0
                            adapter.setData(value.characterList as ArrayList<Character>)
                        }
                    }

                }
            }
        }
    }

    fun showPopUp(){

        val builder = context?.let { AlertDialog.Builder(it) }

        with(builder)
        {
            this?.setView(R.layout.popup)
            this?.show()
        }


    }

}