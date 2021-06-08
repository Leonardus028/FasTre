package com.example.fastre.ui.hospital

import com.example.fastre.core.domain.model.HospitalPhoto


object HospitalPhotoData {
    var photo1 = "https://firebasestorage.googleapis.com/v0/b/fastre.appspot.com/o/img%2Fhospital_img%2Frs-sari-asih-1.jpg?alt=media&token=f6926c05-8223-472a-bce5-2c243c91cc7b"
    var photo2 = "https://firebasestorage.googleapis.com/v0/b/fastre.appspot.com/o/img%2Fhospital_img%2Frs-sari-asih-2.jpg?alt=media&token=12c1b132-b88b-454f-b35a-80a26b8d604a"
    var photo3 = "https://firebasestorage.googleapis.com/v0/b/fastre.appspot.com/o/img%2Fhospital_img%2Frs-sari-asih-3.jpg?alt=media&token=2b16820b-d15d-4795-82a7-7e6f4cb5e3a0"

    private val photo = arrayOf(photo1, photo2, photo3)

    val listHospitalPhoto: ArrayList<HospitalPhoto>
        get(){
            val list = arrayListOf<HospitalPhoto>()
            for (position in photo.indices) {
                val hospitalPhoto = HospitalPhoto()
                hospitalPhoto.photo = photo[position]
                list.add(hospitalPhoto)
            }
            return list
        }
}