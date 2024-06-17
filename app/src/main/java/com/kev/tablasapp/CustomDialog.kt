import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.kev.tablasapp.R

class CustomDialogFragment : DialogFragment() {

    private lateinit var inputEditText: EditText
    private lateinit var btnCancel: Button
    private lateinit var btnOk: Button

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Inflar el diseño del diálogo
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_custom_dialog, null)

        // Obtener referencias a los elementos del diseño
        inputEditText = view.findViewById(R.id.validRes)

        btnOk = view.findViewById(R.id.aceptar)



        btnOk.setOnClickListener {
            val userInput = inputEditText.text.toString()
            // Aquí puedes manejar la acción de Aceptar, por ejemplo, enviar el resultado a la actividad o fragmento que llama al diálogo
           Log.e("----",inputEditText.text.toString())
            //dismiss()
        }

        // Crear el diálogo utilizando AlertDialog.Builder
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(view)

        // Crear el diálogo
        val dialog = builder.create()

        // Aplicar bordes redondeados al fondo del diálogo
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog.window?.decorView?.background = requireContext().resources.getDrawable(R.drawable.roundedcorner)

        return dialog
    }
}
