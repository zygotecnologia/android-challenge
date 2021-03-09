import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.LayoutInputDialogBinding
import com.zygotecnologia.zygotv.utils.showSoftInput
import kotlinx.android.synthetic.main.layout_input_dialog.*


class InputDialog(
    appContext: Context,
    private val title: String,
    private val message: String,
    private val input: MutableLiveData<String>,
    private val autoCompleteList: List<String>,
)
    : Dialog(appContext, R.style.CustomDialogStyle) {

    lateinit var binding : LayoutInputDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        binding = LayoutInputDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        setupViews()
        setupAutoCompleteView()
        context.showSoftInput(binding.autoCompleteTextView)
    }



    private fun setupAutoCompleteView() {
        val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, autoCompleteList)
        binding.autoCompleteTextView.apply {
            setAdapter(adapter)
            setOnItemClickListener { parent, view, position, id ->
                input.value = text.toString()
                dismiss()
            }
        }

    }

    private fun setupViews() {
        binding.tvCustomDialogTitle.text = title
        binding.tvCustomDialogMessage.text = message
    }

}