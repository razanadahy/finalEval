function showModal(id,nom,genre,date,rem) {
    document.getElementById("id").value=id
    document.getElementById("n-nom").value=nom
    document.getElementById("n-genre").value=genre
    document.getElementById("n-date").value=date
    document.getElementById("n-rem").value=rem


    const myModalEl = document.getElementById('myModal')
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()
}
function ajouter(idFacture){
    document.getElementById("id").value=idFacture
    const myModalEl = document.getElementById('myModal')
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()
}