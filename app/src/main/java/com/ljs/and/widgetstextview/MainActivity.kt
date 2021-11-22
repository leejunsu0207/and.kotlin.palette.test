package com.ljs.and.widgetstextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.SeekBar
import androidx.core.widget.addTextChangedListener
import com.ljs.and.widgetstextview.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    val listener by lazy{
        CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                when(buttonView.id){
                    R.id.checkApple-> Log.d("CheckBox", "사과 선택")
                    R.id.checkBanana -> Log.d("CheckBox", "바나나 선택")
                    R.id.checkOrange -> Log.d("CheckBox", "오렌지 선택")
                    R.id.checkBox4 -> Log.d("CheckBox", "checkBox4 선택")
                }
            }else{
                when(buttonView.id){
                    R.id.checkApple -> Log.d("CheckBox", "사과 해제")
                    R.id.checkBanana -> Log.d("CheckBox", "바나나 해제")
                    R.id.checkOrange -> Log.d("CheckBox", "오렌지 해제")
                    R.id.checkBox4 -> Log.d("CheckBox", "checkBox4 해제")
                }

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        thread {    // 서브 스레드
            Thread.sleep(3000)  // 3000밀리초 = 3초
            runOnUiThread { // 메인스레드드
               showProgress(false)

            }
        }


        binding.editText.addTextChangedListener {
            if(it.toString().length >= 8){
                Log.d("EditText", "현재 입력된 값=${it.toString()}")
            }
        }
        
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->

//            when(checkedId){
//                R.id.radioApple -> Log.d("RadioButton", "사과가 선택되었습니다.")
//                R.id.radioBanana -> Log.d("RadioButton", "바나나가 선택되었습니다.")
//                R.id.radioOrange -> Log.d("RadioButton", "오랜지가 선택되었습니다.")
//            }
            when(checkedId){
                R.id.radioApple -> binding.textView.text = "사과"
                R.id.radioBanana -> binding.textView.text = "바나나"
                R.id.radioOrange -> binding.textView.text = "오렌지"
            }

        }
        
        binding.checkApple.setOnCheckedChangeListener (listener)
        binding.checkBanana.setOnCheckedChangeListener (listener)
        binding.checkOrange.setOnCheckedChangeListener (listener)
        binding.checkBox4.setOnCheckedChangeListener (listener)

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.textView.text = "$progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            binding.textView4.text = "$rating"

        }






    }


    fun showProgress(show: Boolean){
//            if (show){
//                binding.progressLayout.visibility = View.VISIBLE
//            }else{
//                binding.progressLayout.visibility = View.GONE
//            }
        binding.progressLayout.visibility = if(show) View.VISIBLE else View.GONE
    }
}