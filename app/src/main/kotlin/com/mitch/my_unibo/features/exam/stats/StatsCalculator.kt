package com.mitch.my_unibo.features.exam.stats

import com.mitch.my_unibo.features.exam.student.StudentExam

interface StatsCalculator {

    // media di ogni anno con pecentuale di miglioramento o peggioramento rispetto al precedente

    /**
     * Number of taken exams for current student.
     *
     * @return number of taken exams
     */
    fun nTakenExams(): Int

    /**
     * Number of total exams for current student.
     *
     * @return number of total exams
     */
    fun nTotalExams(): Int

    /**
     * Total number of CFUs for current student degree.
     *
     * @return total number of CFUs
     */
    fun totalCfu(): Int

    /**
     * Number of taken CFUs for current student.
     *
     * @return number of taken CFUs
     */
    fun takenCfu(): Int

    /**
     * Computes number of non-taken CFUs.
     * Difference between total and taken CFUs.
     *
     * @return number of non-taken CFUs
     */
    fun notTakenCfu(): Int {
        return totalCfu() - takenCfu()
    }

    /**
     * Computes taken CFUs percentage.
     *
     * @return percentage of taken CFUs
     */
    fun percentageTakenCfu(): Float {
        if (totalCfu() == 0) {
            return 0.0f
        }

        return takenCfu().toFloat() / totalCfu().toFloat()
    }

    /**
     * Computes average score between all exams.
     *
     * @return average score
     */
    fun gpa(): Double

    /**
     * Computes starting grade (Base di Laurea) based on average score.
     *
     * @return starting grade
     */
    fun startingGrade(): Double {
        return (gpa() * 11) / 3
    }

    fun gpaFromExams(exams: List<StudentExam>): Double
}
