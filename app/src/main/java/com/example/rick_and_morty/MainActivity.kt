package com.example.rick_and_morty

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty.adapter.CharactersAdapter
import com.example.rick_and_morty.interfaces.Main
import com.example.rick_and_morty.model.Character
import com.example.rick_and_morty.presenter.MainPresenter

class MainActivity : AppCompatActivity(), Main.View {

    private val presenter = MainPresenter()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CharactersAdapter
    lateinit var characters: MutableList<Character>
    lateinit var load: ProgressBar
    lateinit var searchView: SearchView
    lateinit var progressBar: ProgressBar
    var layout = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        load = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.lista)
        progressBar = findViewById(R.id.progressBar)

        characters = mutableListOf<Character>()
        presenter.view = this
        presenter.init()

        configAdapter()
        isLastItemOfList()

        configBar()

    }

    private fun configAdapter() {
        recyclerView.layoutManager = layout
        adapter = CharactersAdapter(characters)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        val optionsMenu = menu?.findItem(R.id.search)
        this.searchView = optionsMenu?.actionView as SearchView
        findCharacters()
        return true
    }

    private fun configBar() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setLogo(R.drawable.ic_bar)
        supportActionBar?.setDisplayUseLogoEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(
            resources.getColor(R.color.color_header)
        ))
    }

    override fun loadCharacters(newCharacters: List<Character>) {
        characters.addAll(newCharacters)
        adapter.notifyDataSetChanged()
    }

    override fun clear() {
        characters.clear()
        adapter.notifyItemRangeRemoved(0, adapter.itemCount)
    }

    override fun findCharacters() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                if (text?.length!! >= 4) {
                    presenter.findCharacters(text.toString())
                    return true
                }
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {

                var copyCharacters = characters.toMutableList()

                if (text?.isNotEmpty()!!) {
                    characters.clear()
                    characters.addAll(
                        copyCharacters.filter { it.name.contains(text, true) }
                    )
                }
                adapter.notifyDataSetChanged()
                return true
            }
        })

        searchView.setOnCloseListener {
            clear()
            presenter.getFirtPage()
            false
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun isLastItemOfList() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (layout.findLastCompletelyVisibleItemPosition() == characters.size - 1) {
                    presenter.getNextPageCharacters()
                }

            }
        })
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }


}