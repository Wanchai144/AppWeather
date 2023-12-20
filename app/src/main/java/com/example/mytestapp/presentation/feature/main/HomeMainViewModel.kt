import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mytestapp.data.model.UserEntity
import com.example.mytestapp.domain.usecase.AttendWorkUseCase
import com.example.mytestapp.presentation.feature.main.base.BaseViewModel
import com.example.mytestapp.presentation.feature.main.winget.AlertDialog
import io.realm.Realm
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class HomeMainViewModel(
    private val realm: Realm,
    private val attendWorkUseCase: AttendWorkUseCase
): BaseViewModel() {

    private val _showList = MutableLiveData<List<UserEntity>>()
    val showList: LiveData<List<UserEntity>> = _showList


    val onSuccess = MutableLiveData<Unit>()

    init {
        getUser()
    }

    fun getUser() = viewModelScope.launch {
        attendWorkUseCase.executeGetData().collect {
            _showList.value = it
        }
    }

    private fun saveData(employeeID:String, message: String) = viewModelScope.launch {
        attendWorkUseCase.executeSaveData(employeeID,message)
            .onStart {}
            .catch { exception ->
                exception.printStackTrace()
            }.onCompletion {}
            .collect { data ->
                Log.d("TAG_saveData", "saveData: success")
                onSuccess.value = data
            }
        }


   fun isEmployeeIdDuplicateInRealm(employeeId: String): Boolean {
        return runCatching {
            realm.where(UserEntity::class.java).equalTo("employeeID", employeeId).findFirst() != null
        }.getOrElse { false }
    }


    fun showDialog(child:FragmentManager,editText:String){
        val dialog =   AlertDialog(
            onDialogNegativeClick = { message: String ->
                saveData(editText,message)
            },
        )
        dialog.show(child, "${screenName}_alert_dialog")

    }

    override fun onCleared() {
        super.onCleared()
        realm.close()
    }
}