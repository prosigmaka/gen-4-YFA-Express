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
              {title: "id", data: "idKurir"},
              {title: "nama", data: "namaKurir"},
              {title: "no telp", data: "noTelpKurir"},

              {
                title: "Action",
                data: null,
                render: function (data, type, row) {
                  return "<button class='btn-success' onclick=formKurir.setEditData('" + data.idKurir + "') style='border-radius: 20%'><i class='fa fa-pencil-alt'></i></button>"+"<span></span>"+
                    "<button class='btn-danger' onclick=actionDelete.deleteConfirm('" + data.idKurir + "') style='border-radius: 20%'><i class='fa fa-trash'></i></button>"

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
    //
    // $.ajax({
    //   type: "GET",
    //   url: "/api/kurir",
    //   contentType: 'application/json',
    //   dataType: 'json',
    //   success: function (res) {
    //     console.log(res)
    //     let s = '<option disabled selected hidden>-- Pilih --</option>';
    //     for (let i = 0; i < res.length; i++) {
    //       s += '<option value=' + res[i].idUser + '>' + res[i].namaUser + '</option>';
    //     }
    //     $("#namaUser").html(s);
    //   }
    // });
  }
};



var formKurir= {
  resetForm: function () {
    $('#form-Kurir')[0].reset();
    $("#idKurir").val("");
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
