package dk.mifu.pmos.vegetablegardening.helpers.callbacks

import android.app.Application
import androidx.databinding.ObservableMap
import dk.mifu.pmos.vegetablegardening.database.AppDatabase
import dk.mifu.pmos.vegetablegardening.database.GardenRepository
import dk.mifu.pmos.vegetablegardening.enums.BedLocation
import dk.mifu.pmos.vegetablegardening.models.Bed
import dk.mifu.pmos.vegetablegardening.models.Coordinate
import dk.mifu.pmos.vegetablegardening.models.Plant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateBedCallback(private val name: String, private val bedLocation: BedLocation, private val application: Application): BedPlantsChangedCallback() {
    override fun onMapChanged(sender: ObservableMap<Coordinate, Plant>?, key: Coordinate?) {
        GlobalScope.launch(Dispatchers.IO) {
            val dao = AppDatabase.getDatabase(application).gardenDao()
            val repository = GardenRepository(dao)
            repository.updateBed(Bed(name, bedLocation, sender!!))
        }
    }
}