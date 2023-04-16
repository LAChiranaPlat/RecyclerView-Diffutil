package com.example.listdiffutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listdiffutil.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Objects

class MainActivity : AppCompatActivity() {

    lateinit var v:ActivityMainBinding
    var ad:adRv= adRv()

    var items:ArrayList<String> = ArrayList()
    var index=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        v=ActivityMainBinding.inflate(layoutInflater)

        setContentView(v.root)

        items.add("BootCamp Android: 15 de Abril")
        items.add("Academia Android: 17 de Abril")
        items.add("PHP Expert: 24 de Abril")
        items.add("Android Developer: 30 de Abril")
        items.add("Android Developer: 31 de Abril")
        items.add("Android Developer: 32 de Abril")
        items.add("Android Developer: 33 de Abril")
        items.add("Android Developer: 34 de Abril")
        items.add("Android Developer: 35 de Abril")
        items.add("Android Developer: 30 de Abril")
        items.add("Android Developer: 30 de Abril")
        items.add("Android Developer: 30 de Abril")
        items.add("Android Developer: 30 de Abril")
        items.add("Android Developer: 30 de Abril")
        items.add("Android Developer: 30 de Abril")
        items.add("Android Developer: 30 de Abril")
        items.add("Android Developer: 30 de Abril")
        items.add("Android Developer: 30 de Abril")


        ad.differ.submitList(items)
        var item=""

        ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                index=viewHolder.adapterPosition
                item=ad.differ.currentList[index]
                Log.i("result",ad.differ.currentList.toString())
                Log.i("result",item.toString())

                items.removeAt(index)
                ad.notifyDataSetChanged()

                Log.i("result",ad.differ.currentList.toString())
                Log.i("result",item.toString())

                Snackbar.make(
                    v.root,
                    "Item Borrado: $item",
                    Snackbar.LENGTH_SHORT
                ).apply {
                    setAction("Deshacer"){
                        Log.i("result",item)
                        Log.i("result",index.toString())
                        items.add(item)
                        ad.notifyItemInserted(index)
                    }
                    show()
                }

            }

        }).attachToRecyclerView(v.lst)

        v.apply {
            lst.layoutManager=LinearLayoutManager(this@MainActivity)
            lst.adapter=ad
            lst.hasFixedSize()

            svFind.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    var res = items.filter { str-> str.lowercase().contains(newText!!.lowercase()) }

                    ad.differ.submitList(res)
                    ad.notifyDataSetChanged()

                    return true

                }

            })

        }



    }
}