package com.example.fastre.ui.news

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fastre.R
import com.example.fastre.core.data.source.Resource
import com.example.fastre.core.ui.NewsAdapter
import com.example.fastre.core.ui.ViewModelFactory
import com.example.fastre.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {
    private lateinit var newsViewModel: NewsViewModel

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoToBookmarked.setOnClickListener {
            val moveIntent = Intent(context, BookmarkedNewsActivity::class.java)
            startActivity(moveIntent)
        }

        if (activity != null) {
            val newsAdapter = NewsAdapter()
            val factory = ViewModelFactory.getInstance(requireActivity())
            newsViewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]

            newsViewModel.news.observe(viewLifecycleOwner, { news ->
                if (news != null) {
                    when (news) {
                        is Resource.Loading -> {
                            Log.d("resource", "observe news: loading" )
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            Log.d("resource", "observe news: succes" )
                            binding.progressBar.visibility = View.GONE
                            newsAdapter.setData(news.data)
                        }
                        is Resource.Error -> {
                            Log.d("resource", "observe news: error" )
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(context, getString(R.string.error), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })

            with(binding.rvNews) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = newsAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}