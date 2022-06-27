package com.propil.beeradvisor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.ChipGroup
import com.propil.beeradvisor.database.BeerModel
import org.w3c.dom.Text

private const val ARG_BEER_ID = "beer_id"
private const val TAG = "BeerDetailFragment"

class BeerDetailFragment: Fragment() {

    private lateinit var beerModel: BeerModel
    private lateinit var beerName: TextView
    private lateinit var beerImage: ImageView
    private lateinit var beerChips: ChipGroup
    private lateinit var beerDescription: TextView

    private val beerDetailViewModel: BeerDetailViewModel by lazy {
        ViewModelProvider(this)[BeerDetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beerModel = BeerModel()
        val beerId: Long = arguments?.getSerializable(ARG_BEER_ID) as Long
        Log.d(TAG, "args bundle beer id: $beerId")

        //TODO: Get the data from item in recyclerview
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beer_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        beerName = view.findViewById(R.id.beer_name) as TextView
        beerImage = view.findViewById(R.id.beer_image_detail) as ImageView
        beerChips = view.findViewById(R.id.chip_group) as ChipGroup
        beerDescription = view.findViewById(R.id.beer_description) as TextView

        beerDetailViewModel.beerDetailLiveData.observe(viewLifecycleOwner){beerModel ->
            beerModel?.let { this.beerModel = beerModel }
            updateUI()
        }
    }

    private fun updateUI(){
        beerName.text = beerModel.beerName
        beerDescription.text = beerModel.beerDescription
        beerImage.setImageResource(R.drawable.wine_bottle)
//        beerChips TODO: I don't know how set the values for BeerModel.tags right now

    }

    companion object {
        //  статическая функция для передачи аргументов фрагмента
        fun newInstance(beerId: Long): BeerDetailFragment {
            val args = Bundle().apply { putSerializable(ARG_BEER_ID, beerId) }

            return BeerDetailFragment().apply { arguments = args }
        }
    }

}