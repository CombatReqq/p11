package ru.btpit.p2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import ru.btpit.p2.databinding.FragmentNewPostBinding
import ru.netology.nmedia.util.AndroidUtils
import util.StringArg
import viewmodel.PostViewModel

class NewPostFragment : Fragment() {

    companion object {
        var Bundle.textArg: String? by StringArg
    }

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewPostBinding.inflate(
            inflater,
            container,
            false
        )
        if (arguments?.textArg == null )
        {
            binding.name.text ="Добавить пост"
        }
        else
        {
            binding.name.text ="Изменение поста"
        }

        arguments?.textArg
            ?.let(binding.edit::setText)

        binding.ok.setOnClickListener {
            viewModel.changeContent(binding.edit.text.toString())
            println(binding.edit.text.toString())
            viewModel.save()
            AndroidUtils.hideKeyboard(requireView())
            findNavController().navigateUp()
        }
        return binding.root
    }
}






