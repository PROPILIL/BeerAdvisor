package com.propil.beeradvisor

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.propil.beeradvisor.database.BeerModel

private const val TAG = "BeerListFragment"

class BeerListFragment: Fragment() {

    interface Callbacks {
        fun onBeerSelected(beerId: Long)
    }

    private var callbacks: Callbacks? = null
    private lateinit var beerRecyclerView: RecyclerView
    private lateinit var beerModel: BeerModel
    private var adapter: BeerAdapter? = BeerAdapter(emptyList())


    private val beerListViewModel: BeerListViewModel by lazy{
        ViewModelProvider(this)[BeerListViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beer_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        beerRecyclerView = view.findViewById(R.id.beer_recycler_view) as RecyclerView
        beerRecyclerView.layoutManager = GridLayoutManager(context, 2)


        beerListViewModel.beerListLiveData.observe(viewLifecycleOwner) { beerModel ->
            beerModel?.let {
                Log.i(TAG, "Got beers ${beerModel.size}")
                updateUI(beerModel)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun updateUI(beer: List<BeerModel>){
        adapter = BeerAdapter(beer)
        beerRecyclerView.adapter = adapter
    }

    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//
    //--------------------------------BEER  VIEWHOLDER-------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//
    private inner class BeerViewHolder(view: View)
        :RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var beer: BeerModel

        val beerImage: ImageView = itemView.findViewById(R.id.beer_image_card)
        val beerLabel: TextView = itemView.findViewById(R.id.beer_label)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(beer: List<BeerModel>){
            this.beer = beerModel
            beerLabel.text = this.beer.beerName
        }

        override fun onClick(p0: View?) {
            callbacks?.onBeerSelected(this.beer.id)
        }
    }
    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//
    //--------------------------------BEER  ADAPTER-------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//

    private inner class BeerAdapter(var allBeers: List<BeerModel>)
        :RecyclerView.Adapter<BeerViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
            val view = layoutInflater.inflate(R.layout.card_item_beer, parent, false)

            return BeerViewHolder(view)
        }

        override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
            holder.bind(allBeers)
        }

        override fun getItemCount(): Int = allBeers.size


    }

    companion object {
        fun newInstance(): BeerListFragment { // получение экземпляра фрагмента. Активити будет юзать его вместо интентов
            return BeerListFragment()
        }
    }

}