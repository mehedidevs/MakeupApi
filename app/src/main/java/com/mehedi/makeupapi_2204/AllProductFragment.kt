package com.mehedi.makeupapi_2204

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.mehedi.makeupapi_2204.databinding.FragmentAllProductBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class AllProductFragment : Fragment(),
    ProductAdapter.ProductListener {

    lateinit var adapter: ProductAdapter

    @Inject
    lateinit var service: ProductService


    lateinit var binding: FragmentAllProductBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllProductBinding.inflate(inflater, container, false)

        adapter = ProductAdapter(this)

        binding.productItemRcv.adapter = adapter


        var callApiService = service.getAllProducts()

        callApiService.enqueue(object : retrofit2.Callback<List<ResponseProduct>> {
            override fun onResponse(
                call: Call<List<ResponseProduct>>,
                response: Response<List<ResponseProduct>>
            ) {

                if (response.code() == 200) {

                    adapter.submitList(response.body())


                }


            }

            override fun onFailure(call: Call<List<ResponseProduct>>, t: Throwable) {

                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_LONG).show()

            }
        })




        return binding.root
    }


    companion object {

    }

    override fun productClickedListener(productId: Int) {

        val bundle = Bundle()
        bundle.putInt(DetailsProductFragment.PRODUCT_KEY, productId)



        findNavController().navigate(
            R.id.action_allProductFragment_to_detailsProductFragment,
            bundle
        )

    }
}