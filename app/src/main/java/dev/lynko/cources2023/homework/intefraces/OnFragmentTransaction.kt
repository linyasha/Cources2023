package dev.lynko.cources2023.homework.intefraces

interface OnFragmentTransaction {
    fun onTransactionToResultFragment(min:Int, max:Int)
    fun backToStartFragment(result: Int)
}