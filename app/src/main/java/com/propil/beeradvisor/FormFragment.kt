package com.propil.beeradvisor

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.propil.beeradvisor.database.BeerModel

class FormFragment : Fragment() {
    private lateinit var beerModel: BeerModel
    private val formViewModel: FormViewModel by lazy {
        ViewModelProvider(this)[FormViewModel::class.java]
    }
    override fun onStop() {
        super.onStop()
        formViewModel.saveBeer(beerModel)

    }
}