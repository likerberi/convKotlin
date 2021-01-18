fun sortFunctions(index : Int) {
        when(index) {
            INDEX_MONTHLY -> {
                if (monthlyFlag) {
                    monthlyFlag = false
                    lists.sortWith(compareBy { it.lease_monthly })
                } else {
                    lists.sortWith(compareBy { it.lease_monthly })
                    lists.reverse()
                    monthlyFlag = true
                }
            }

            INDEX_DEPOSIT -> {
                if (depositFlag) {
                    depositFlag = false
                    lists.sortWith(compareBy { it.lease_deposit })
                } else {
                    lists.sortWith(compareBy { it.lease_deposit })
                    lists.reverse()
                    depositFlag = true
                }
            }

            INDEX_SPACING -> {
                if (sizeFlag) {
                    sizeFlag = false
                    lists.sortWith(compareBy { it.space })
                } else {
                    lists.sortWith(compareBy { it.space })
                    lists.reverse()
                    sizeFlag = true
                }
            }
        }
        val adapter = CustomAdapter(lists)
        recyclerView.adapter = adapter
    }

///////////////////////////////////////////////////////////////// former - integration
fun sortDeposit() {
    if (depositFlag) {
        depositFlag = false
        lists.sortWith(compareBy { it.lease_deposit })
        val adapter = CustomAdapter(lists)
        recyclerView.adapter = adapter
    } else {
        lists.sortWith(compareBy { it.lease_deposit })
        lists.reverse()
        val adapter = CustomAdapter(lists)
        recyclerView.adapter = adapter
        depositFlag = true
    }
}

fun sortMonthly() {
    if (monthlyFlag) {
        monthlyFlag = false
        lists.sortWith(compareBy { it.lease_monthly })
        val adapter = CustomAdapter(lists)
        recyclerView.adapter = adapter
    } else {
        lists.sortWith(compareBy { it.lease_monthly })
        lists.reverse()
        val adapter = CustomAdapter(lists)
        recyclerView.adapter = adapter
        monthlyFlag = true
    }
}

fun sortSize() {
    if (sizeFlag) {
        sizeFlag = false
        lists.sortWith(compareBy { it.space })
        val adapter = CustomAdapter(lists)
        recyclerView.adapter = adapter
    } else {
        lists.sortWith(compareBy { it.space })
        lists.reverse()
        val adapter = CustomAdapter(lists)
        recyclerView.adapter = adapter
        sizeFlag = true
    }
}