package com.example.news.data/*
import android.os.Bundle
import androidx.fragment.app.Fragment

// Fragment
class ProductFragment : Fragment() {

    companion object {

        private const val ARG_PRODUCT_ID = "arg_product_id"
        private const val CART_ANIMATION_DELAY_MS = 1000L

        fun newInstance(id: Int): ProductFragment {
            val args = Bundle().apply { putInt(ARG_PRODUCT_ID, id) }
            return ProductFragment().apply { arguments = args }
        }
    }

    private val productModel: ProductViewModel by lazy {
        ProductViewModel(
            id = requireArguments().getInt(ARG_PRODUCT_ID),
            context = requireContext()
        )
    }

    private var _binding: FragmentProductBinding? = null
    private val binding: FragmentProductBinding
        get() = _binding ?: throw IllegalStateException(
            "Illegal fragment state (${viewLifecycleOwner.lifecycle.currentState}) for view binding"
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productModel.inCartCount
            .observe(this) { count ->
                if (count == 0) {
                    binding.addToCartButton.text = requireContext().getString(R.string.add_to_cart)
                } else {
                    val text = requireContext().getString(R.string.in_cart) + " (" + count + ")"
                    binding.addToCartButton.text = text
                }
            }

        productModel.favouriteButtonText
            .observe(this) {
                binding.favouriteButton.text = it
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productModel.product
            .observe(viewLifecycleOwner) { product ->
                with(binding) {
                    productImage.loadImage(product.imageUrls[0])
                    title.text = product.title
                    product.description?.image?.let {
                        it.url?.let {
                            descriptionImage.loadImage(it)
                        }
                    }

                    description.text = product.description!!.text
                }
            }

        binding.root.postDelayed(CART_ANIMATION_DELAY_MS) {
            showCartAnimation()
        }
    }

    private fun showCartAnimation() {
        binding.cartLogo.showAnimation()
    }
}

//View model

class ProductViewModel(
    private val id: Int,
    private val context: Context
) : ViewModel() {

    val product: MutableLiveData<Product> = MutableLiveData<Product>()
    val inCartCount: LiveData<Int>
    val favouriteButtonText: LiveData<String>

    private val productRepository: ProductRepository = Container.productRepository
    private val cartRepository: CartRepository = Container.cartRepository
    private val favouriteRepository: FavouriteRepository = Container.favouriteRepository

    init {

        viewModelScope.launch {
            product.value = productRepository.getProduct(id)
        }

        inCartCount = cartRepository.getInCartCount(id).asLiveData()
        favouriteButtonText = favouriteRepository.isFavourite(id)
            .map { isFavourite ->
                val resId = if (isFavourite) {
                    R.string.remove_from_favourite_button_text
                } else {
                    R.string.add_to_favourite_button_text
                }
                context.getString(resId)
            }
            .asLiveData()
    }
}

// Other (не требует ревью)

interface CartRepository {
    fun getInCartCount(id: Int): Flow<Int>
}

interface ProductRepository {
    suspend fun getProduct(id: Int): Product
}

interface FavouriteRepository {
    fun isFavourite(id: Int): Flow<Boolean>
}

data class Product(
    val id: Int,
    val title: String,
    val description: Description?,
    val imageUrls: List<String>
)

data class Description(
    val text: String?,
    val image: Image?
)

data class Image(
    val url: String?,
    val name: String?
)
*/
