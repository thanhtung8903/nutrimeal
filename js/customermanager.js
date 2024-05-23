const search = document.querySelector(".customer-tabler-search input");
table_rows = document.querySelectorAll(".customer-table-body tbody tr");
table_headings = document.querySelectorAll(".customer-table-body thead th");
search.addEventListener("input", searchtable);
function searchtable() {
  table_rows.forEach((row, i) => {
    let table_data = row.textContent.toLowerCase();
    search_data = search.value.toLowerCase();
    row.classList.toggle("hide", table_data.indexOf(search_data) < 0);
    row.style.setProperty("--delay", i / 25 + "s");
  });
  document.querySelectorAll("tbody tr:not(.hide)").forEach((visible_row, i) => {
    visible_row.style.backgroundColor =
      i % 2 == 0 ? "transparent" : "#0000000b";
  });
}

table_headings.forEach((head, i) => {
  let sort_arc = true;
  head.onclick = () => {
    table_headings.forEach((head) => head.classList.remove("active"));
    head.classList.add("active");
    document
      .querySelectorAll("td")
      .forEach((td) => td.classList.remove("active"));
    table_rows.forEach((row) => {
      row.querySelectorAll("td")[i].classList.add("active");
    });
    head.classList.toggle("asc", sort_arc);
    sort_arc = head.classList.contains("asc") ? false : true;
    sortTable(i, sort_arc);
  };
});
function sortTable(column, sort_arc) {
  [...table_rows]
    .sort((a, b) => {
      let first_row = a
        .querySelectorAll("td")
        [column].textContent.toLowerCase();
      second_row = b.querySelectorAll("td")[column].textContent.toLowerCase();
      return sort_arc
        ? first_row < second_row
          ? 1
          : -1
        : first_row < second_row
        ? -1
        : 1;
    })
    .map((sorted_row) =>
      document.querySelector("tbody").appendChild(sorted_row)
    );
}

let listOrder = document.getElementsByClassName("customer-order")[0];
let backgrouptable = document.getElementsByClassName("customer-table-body")[0];

function closeListOrder() {
  listOrder.classList.remove("open-customer-order");
  backgrouptable.classList.remove("blur-background");
}
function openListOrder(id) {
  listOrder.classList.add("open-customer-order");
  backgrouptable.classList.add("blur-background");

  let rows = listOrder.getElementsByTagName("tr");

  for (let i = 0; i < rows.length; i++) {
    let cells = rows[i].getElementsByTagName("td");

    if (cells.length > 0) {
      let firstCell = cells[0];
      let firstCellValue = firstCell.textContent || firstCell.innerText;
      if (firstCellValue === id) {
        rows[i].style.display = "";
      } else {
        rows[i].style.display = "none";
      }
    }
  }
}
openListOrder(id);
