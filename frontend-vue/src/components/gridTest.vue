<template>
  <grid
    :auto-width="autoWidth"
    :cols="cols"
    :from="from"
    :language="language"
    :pagination="pagination"
    :rows="rows"
    :search="search"
    :server="server"
    :sort="sort"
    :width="width"
  ></grid>
</template>

<script>
  import Grid from 'gridjs-vue'
 // import TicketClient from '../clients/table-client.js'
  export default {
    name: 'gridTest',
    components: {
      Grid
    },
    data() {
      return {
          cols: ['One-way', 'Origin', 'Destination', 'Depart', 'Return', 'Company', 'Count'],
 rows: [
    
          ['row 1 col 1', 'row 1 col 2'], 
          ['row 1 col 1', 'row 1 col 2'],
          ['row 1 col 1', 'row 1 col 2']
         
        ],
         
         pagination: true  || {},
      }
    }
  }
</script>