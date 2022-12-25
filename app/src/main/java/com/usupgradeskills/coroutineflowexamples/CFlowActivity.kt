package com.usupgradeskills.coroutineflowexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.usupgradeskills.coroutineflowexamples.databinding.ActivityCflowBinding
import com.usupgradeskills.coroutineflowexamples.repo.CFlowRepo
import com.usupgradeskills.coroutineflowexamples.viewModel.CFlowViewModel
import com.usupgradeskills.coroutineflowexamples.viewModel.CFlowViewModelFactory
import kotlinx.coroutines.launch

class CFlowActivity : AppCompatActivity() {
    lateinit var binding: ActivityCflowBinding

    lateinit var viewModel : CFlowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cflow)
        val repo = CFlowRepo()
        viewModel = ViewModelProvider(this, CFlowViewModelFactory(repo))[CFlowViewModel::class.java]

        binding.btnBuilder.setOnClickListener {
            viewModel.getFlowOfData()
        }

        binding.btnBuilder1.setOnClickListener {
            viewModel.getAsFlowData()
        }

        binding.btnBuilder2.setOnClickListener {
            viewModel.getFlowWithString()
        }

        binding.btnBuilder3.setOnClickListener {
            viewModel.getChannelFlow()
        }

        binding.filterOperator.setOnClickListener {
            viewModel.getFilterOperator()
        }

        binding.mapOperator.setOnClickListener {
            viewModel.getMapOperator()
        }

        binding.takeOperator.setOnClickListener {
            viewModel.getTakeOperator()
        }

        binding.takeWhileOperator.setOnClickListener {
            viewModel.getTakeWhileOperator()
        }

        binding.dropOperator.setOnClickListener {
            viewModel.getDropOperator()
        }

        binding.dropWhileOperator.setOnClickListener {
            viewModel.getDropWhileOperator()
        }

        binding.combineOperator.setOnClickListener {
            viewModel.getCombineOperator()
        }

        binding.zipOperator.setOnClickListener {
            viewModel.getZipOperator()
        }

        binding.flattenOperator.setOnClickListener {
            viewModel.getFlattenMergeOperator()
        }

        binding.flatMapConnectOperator.setOnClickListener {
            viewModel.getFlatMapConnectOperator()
        }

        binding.flatMapMergeOperator.setOnClickListener {
            viewModel.getFlatMapMergeOperator()
        }

        binding.flatMapLatestOperator.setOnClickListener {
            viewModel.getFlatMapLatestOperator()
        }

        binding.retryOperator.setOnClickListener {
            viewModel.getRetryOperator()
        }

        binding.buffer.setOnClickListener {
            viewModel.getBuffer()
        }

        binding.conflate.setOnClickListener {
            viewModel.getConflate()
        }

        binding.collectLatest.setOnClickListener {
            viewModel.getConflateLatest()
        }
        ////////////////////////////// observing

        lifecycleScope.launch {
            viewModel.firstData.collect{
                binding.textView.text = it
            }
        }

        lifecycleScope.launch {
            viewModel.secondData.collect{
                binding.textView.text = it.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.thirdData.collect{
                binding.textView.text = it.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.fourthData.collect{
                binding.textView.text = it.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.filterData.collect{
                binding.textView.text = it.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.mapData.collect {
                binding.textView.text = it.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.takeOperator.collect {
                binding.textView.text = it.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.takeWhileOperator.collect {
                binding.textView.text = it.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.dropOperator.collect {
                binding.textView.text = it.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.dropWhileOperator.collect {
                binding.textView.text = it.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.combineOperator.collect {
                binding.textView.text = it.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.zipOperator.collect {
                binding.textView.text = it.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.flattenMergeOperator.collect {
                binding.textView.text = it.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.flatMapConnectOperator.collect {
                binding.textView.text = it.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.flatMapMergeOperator.collect {
                binding.textView.text = it.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.flatMapLatest.collect {
                binding.textView.text = it.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.bufferData.collect {
                binding.textView.text = it.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.conflateData.collect {
                binding.textView.text = it.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.collectLatestData.collect {
                binding.textView.text = it.toString()
            }
        }

    }
}