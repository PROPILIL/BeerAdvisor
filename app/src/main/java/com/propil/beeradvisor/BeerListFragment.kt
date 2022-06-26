package com.propil.beeradvisor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.propil.beeradvisor.database.BeerModel

private const val TAG = "BeerListFragment"

class BeerListFragment: Fragment() {

    private lateinit var beerRecyclerView: RecyclerView
    private lateinit var beerModel: BeerModel
    private var adapter: BeerAdapter? = null


    private val beerListViewModel: BeerViewModel by lazy{
        ViewModelProvider(this)[BeerViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total beers: ${beerListViewModel.beerListLiveData}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflater.inflate(R.layout.fragment_beer_list, container, false)

        beerRecyclerView = view?.findViewById(R.id.beer_recycler_view) as RecyclerView
        beerRecyclerView.layoutManager = GridLayoutManager(context, 2)

        updateUI()

        return view
    }

    private fun updateUI(){
        val allBeers = beerListViewModel.beerListLiveData
        adapter = BeerAdapter(allBeers)
        beerRecyclerView.adapter = adapter
    }
    private inner class BeerViewHolder(view: View)
        :RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var beer: BeerModel

        val beerImage: ImageView = itemView.findViewById(R.id.beer_image)
        val beerLabel: TextView = itemView.findViewById(R.id.beer_label)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(beer: BeerModel){
            this.beer = beer
            beerLabel.text = this.beer.beerName
        }

        override fun onClick(p0: View?) {
            Log.d(TAG, "${beerModel.beerName} selected")
        }
    }


    private inner class BeerAdapter(var allBeers: LiveData<List<BeerModel>>)
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