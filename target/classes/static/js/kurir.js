var tabelKurir = {
  create: function () {
    if ($.fn.DataTable.isDataTable('#tabelKurir')) {
      $('#tabelKurir').DataTable().clear();
      $('#tabelKurir').DataTable().destroy();
    }

    $.ajax({
      url: '/api/kurir',
      method: 'get',
      contentType: 'application/json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#tabelKurir').DataTable({
            data: res,
            columns: [
              {title: "ID Kurir", data: "idKurir"},
              {title: "Nama Kurir", data: "namaKurir"},
              {title: "No Telp", data: "noTelpKurir"},
              {
                title: "Foto",
                data: null,
                render: function (data, type, row) {
                  return "<img src='/api/kurir/getFoto/"+data.idKurir+"' alt='myImage' style='border-radius: 5px; width: 100px'>"
                }
              },

              {
                title: "Action",
                data: null,
                render: function (data, type, row) {
                  return "<button class='btn-dark' onclick=formKurir.setEditData('" + data.idKurir + "') style='border-radius: 15%'><i class='far fa-edit'></i></button>"+"<span>   </span>"+
                    "<button class='btn-danger' onclick=actionDelete.deleteConfirm('" + data.idKurir + "') style='border-radius: 15%'><i class='far fa-trash-alt'></i></button>"

                }
              }
            ]
          });
        } else {

        }
      },
      error: function (err) {
        console.log(err);
      }
    });
  }
};



var formKurir= {
  resetForm: function () {
    $('#form-Kurir')[0].reset();
    $("#idKurir").val("");
    $('#thumbnail').removeAttr('src')
  },
  setEditData: function (id) {
    formKurir.resetForm();

    $.ajax({
      url: '/api/kurir/' + id,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#form-Kurir').fromJSON(JSON.stringify(res));
          $('#modal-kurir').modal('show')

        } else {

        }
      },
      erorrr: function (err) {
        console.log(err);
      }
    });
  }
};

var actionDelete = {
  deleteConfirm : function (id) {
    $.ajax({
      url: '/api/kurir/'+ id,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#form-Kurir').fromJSON(JSON.stringify(res));
          $('#modal-delete').modal('show')
        } else {

        }
      },
      erorrr: function (err) {
        console.log(err);
      }
    });
  },
  deleteRowData: function () {
    if ($('#form-Kurir').parsley().validate()) {
      var dataResult = getJsonForm($("#form-Kurir").serializeArray(), true);

      $.ajax({
        url: '/api/kurir/' + dataResult.idKurir,
        method: 'delete',
        success: function () {
          tabelKurir.create();
          $('#modal-delete').modal('hide')
        },
        erorrr: function (err) {
          console.log(err);
        }
      });
    }
  }
}
