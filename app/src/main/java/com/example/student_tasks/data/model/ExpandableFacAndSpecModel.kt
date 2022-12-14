package com.example.student_tasks.data.model

class ExpandableFacAndSpecModel {
    companion object {
        const val PARENT = 1
        const val CHILD = 2
    }

    lateinit var facultyParent: FacultyAndSpecialityModel.Faculty
    var type: Int
    lateinit var facultyChild: FacultyAndSpecialityModel.Faculty.Speciality
    var isExpanded: Boolean
    private var isCloseShown: Boolean

    constructor(
        type: Int,
        facultyParent: FacultyAndSpecialityModel.Faculty,
        isExpanded: Boolean = false,
        isCloseShown: Boolean = false
    ) {
        this.type = type
        this.facultyParent = facultyParent
        this.isExpanded = isExpanded
        this.isCloseShown = isCloseShown
    }

    constructor(
        type: Int,
        facultyChild: FacultyAndSpecialityModel.Faculty.Speciality,
        isExpanded: Boolean = false,
        isCloseShown: Boolean = false
    ) {
        this.type = type
        this.facultyChild = facultyChild
        this.isExpanded = isExpanded
        this.isCloseShown = isCloseShown
    }
}