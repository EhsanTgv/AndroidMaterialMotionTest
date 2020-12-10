package com.taghavi.androidmaterialmotion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

private const val ARG_PLANET_ID = "planet_id"

class PlanetFragment : Fragment() {
    private var _binding: PlanetFragmentBinding? = null
    private val binding get() = _binding!!

    private var planetId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            planetId = it.getInt(ARG_PLANET_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PlanetFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val planet = planets.singleOrNull { it.id == planetId }
        planet?.let {
            Glide.with(requireContext())
                .load(it.mainImage)
                .into(binding.mainImage)

            binding.planetName.text = planet.name
            binding.planetDescription.text = planet.description
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(planetId: Int) =
            PlanetFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PLANET_ID, planetId)
                }
            }
    }
}