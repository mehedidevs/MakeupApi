package com.mehedi.makeupapi_2204

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.mehedi.makeupapi_2204.databinding.FragmentDetailsProductBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class DetailsProductFragment : Fragment() {

    lateinit var binding: FragmentDetailsProductBinding

    @Inject
    lateinit var service: ProductService


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsProductBinding.inflate(inflater, container, false)

        val productID = requireArguments().getInt(PRODUCT_KEY)


        service.getAllProductById(productID).enqueue(object : Callback<ResponseProduct> {
            override fun onResponse(
                call: Call<ResponseProduct>,
                response: Response<ResponseProduct>
            ) {

                if (response.code() == 200) {
                    Log.i("TAG", "onResponse: ${response.body()} ")

                    response.body()?.let {

                        binding.apply {
                            productName.text = it.name
                            productBrand.text = "Brand ${it.brand}"
                            productPrice.text = " ${it.priceSign}: ${it.price}( ${it.currency})"
                            productImage.load(it.imageLink)
                            productDescription.text = it.description
                            productCategory.text = it.category
                            productType.text = it.productType
                            productTags.text = it.tagList.toString()


                        }
                    }


                }

            }

            override fun onFailure(call: Call<ResponseProduct>, t: Throwable) {

            }
        })







        return binding.root
    }

    companion object {
        const val PRODUCT_KEY = "product_id_key"


    }
}