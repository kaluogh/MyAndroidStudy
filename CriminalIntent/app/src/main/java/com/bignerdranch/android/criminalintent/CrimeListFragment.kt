package com.bignerdranch.android.criminalintent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CrimeListFragment : Fragment() {

    companion object {
        fun newInstance() = CrimeListFragment()
    }

    private lateinit var crimeViewModel: CrimeListViewModel
    private lateinit var crimeListView: RecyclerView
    private var crimeAdapter:CrimeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.crime_list_fragment, container, false)
        crimeListView = view.findViewById(R.id.crime_list_fragment)
        crimeListView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        crimeViewModel = ViewModelProvider(this).get(CrimeListViewModel::class.java)
        // TODO: Use the ViewModel
        updateUI()
    }

    private fun updateUI() {
        val crimes:List<Crime> = crimeViewModel.crimes
        crimeAdapter = CrimeAdapter(crimes)
        crimeListView.adapter = crimeAdapter
    }

    private inner class CrimeHolder(view: View):RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var crime:Crime
        private var titleTextView:TextView = itemView.findViewById(R.id.crime_title)
        private var dateTextView:TextView = itemView.findViewById(R.id.crime_date)
        private var solveImageView:ImageView = itemView.findViewById(R.id.crime_solved_imageView)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(crime: Crime){
            this.crime = crime
            titleTextView.text = crime.title
            dateTextView.text = crime.date.toString()
            solveImageView.visibility = when{
                crime.isSolved -> View.VISIBLE
                else  -> View.GONE
            }

        }

        override fun onClick(view: View) {
            Toast.makeText(context, "${crime.title} pressed!", Toast.LENGTH_SHORT).show()
        }
    }

    private inner class CrimeAdapter(var crimes: List<Crime>):RecyclerView.Adapter<CrimeHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val view = layoutInflater.inflate(R.layout.crime_list_item, parent, false)
            return CrimeHolder(view)
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position]
            holder.bind(crime)
        }

        override fun getItemCount(): Int {
            return crimes.size
        }

    }
}