var tableBarang = {
  create: function () {
    // jika table tersebut datatable, maka clear and dostroy
    if ($.fn.DataTable.isDataTable('#tableBarang')) {
      //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
      $('#tableBarang').DataTable().clear();
      $('#tableBarang').DataTable().destroy();
    }

    $.ajax({
      url: '/api/barang',
      method: 'get',
      contentType: 'application/json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#tableBarang').DataTable({
            data: res,
            columns: [
              {title: "Id Barang", data: "idBrang"},
              {title: "Nama ", data: "namaBarang"},
              {title: "jumlah", data: "jumlahBarang"},
              {title: "Keterangan", data: "keteranganBarang"},
              {title: "Id Berat Barang", data: "idBeratBarang"},
              {title: "Kategori Berat Barang", data: "kategoriBeratBarang"},
              {title: "Biaya Berat Barang", data: "biayaKategori"},
              {title: "Id Layanan", data: "idLayanan"},
              {title: "Kategori Layanan", data: "kategoriLayanan"},
              {title: "Biaya Layanan", data: "biayaLayanan"},
              {title: "Id Pengirim", data: "idPengirim"},
              {title: "Nama Pengirim", data: "namaPengirim"},
              {title: "Telp Pengirim", data: "telpPengirim"},
              {title: "Kota Pengirim", data: "kotaPengirim"},
              {title: "Alamat Pengirim", data: "alamatPengirim"},
              {title: "Kode Pos Pengirim", data: "kodePosPengirim"},
              {title: "Id Penerima", data: "idPenerima"},
              {title: "Nama Penerima", data: "namaPenerima"},
              {title: "Telp Penerima", data: "telpPenerima"},
              // {title: "Kota Penerima", data: "kotaPenerima"},
              {title: "Alamat Penerima", data: "alamatPenerima"},
              {title: "Kode Pos Penerima", data: "kodePosPenerima"},

              {
                title: "Action",
                data: null,
                render: function (data, type, row) {
                  return "<button class='btn-success' onclick=formDropOff.setEditData('" + data.id + "') style='border-radius: 20%'><i class='fa fa-pencil-alt'></i></button>" + "<span></span>" +
                    "<button class='btn-danger' onclick=actionDelete.deleteConfirm('" + data.id + "') style='border-radius: 20%'><i class='fa fa-trash'></i></button>"

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

var formDropOff = {

  resetForm: function () {
    $('#formDropOff')[0].reset();
    $("#id").val("");
    $("#idPengirim").val("");
    $("#idPenerima").val("");

  },
  saveForm: function () {
    if ($('#formDropOff').parsley().validate()) {
      var dataResult = getJsonForm($("#formDropOff").serializeArray(), true);
      console.log(dataResult)

      $.ajax({
        url: '/api/barang',
        method: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(dataResult),
        success: function (res, status, xhr) {
          if (xhr.status == 200 || xhr.status == 201) {
            formDropOff.resetForm();
            tableBarang.create();
            $('#modal-barang').modal('hide')

          } else {

          }
        },
        erorrr: function (err) {
          console.log(err);
        }
      });
    }
  },
  cekHargaForm: function () {
    if ($('#formDropOff').parsley().validate()) {
      var dataResult = getJsonForm($("#formDropOff").serializeArray(), true);
      console.log(dataResult)

      $.ajax({
        url: '/api/barang/cek',
        method: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(dataResult),
        success: function (res, status, xhr) {
          if (xhr.status == 200 || xhr.status == 201) {
            var s = '<option value="-1">Pilih Layanan</option>';
            for (var i = 0; i < data.length; i++) {
              s += '<option value="' + data[i].idLayanan + '">' + data[i].kategoriLayanan + '</option>';
            }
            $("#layanan").append(s);

          } else {

          }
        },
        erorrr: function (err) {
          console.log(err);
        }
      });
    }
  },
  setEditData: function (id) {
    formDropOff.resetForm();

    $.ajax({
      url: '/api/barang/' + id,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#formDropOff').fromJSON(JSON.stringify(res));
          $('#modal-barang').modal('show')

        } else {

        }
      },
      erorrr: function (err) {
        console.log(err);
      }
    });
  }
};
var dropdown = {
  pilihLayanan: function () {
    $.ajax({
      type: "GET",
      url: "/api/layanan",
      contentType: 'application/json',
      dataType: 'json',
      success: function (data) {
        var s = '<option value="-1">Pilih Layanan</option>';
        for (var i = 0; i < data.length; i++) {
          s += '<option value="' + data[i].idLayanan + '">' + data[i].kategoriLayanan + '</option>';
        }
        $("#kategoriLayanan").append(s);
      }
    });
  },

  pilihBeratBarang: function () {
    $.ajax({
      type: "GET",
      url: "/api/beratBarang",
      contentType: 'application/json',
      dataType: 'json',
      success: function (data) {
        var s = '<option value="-1">Pilih Berat Barang</option>';
        for (var i = 0; i < data.length; i++) {
          s += '<option value="' + data[i].idBeratBarang + '">' + data[i].kategoriBeratBarang + '</option>';
        }
        $("#kategoriBeratBarang").append(s);
      }
    });
  },

  pilihProvinsi: function () {
    $.ajax({
      type: "GET",
      url: "/api/provinsi",
      contentType: 'application/json',
      dataType: 'json',
      success: function (data) {
        var s = '<option value="-1">Pilih Provinsi</option>';
        for (var i = 0; i < data.length; i++) {
          s += '<option value="' + data[i].province_id + '">' + data[i].province + '</option>';
        }
        $("#province").append(s);
      }
    });
  },

  pilihKota: function () {
    $.ajax({
      type: "GET",
      url: "/api/kotaRaja",
      contentType: 'application/json',
      dataType: 'json',
      success: function (data) {
        var s = '<option value="-1">Pilih Kota Asal</option>';
        for (var i = 0; i < data.length; i++) {
          s += '<option value="' + data[i].city_id + '">' + data[i].city_name + '</option>';
        }
        $("#city_name").append(s);
      }
    });
  },

  pilihProvinsiPenerima: function () {
    $.ajax({
      type: "GET",
      url: "/api/provinsi",
      contentType: 'application/json',
      dataType: 'json',
      success: function (data) {
        var s = '<option value="-1">Pilih Provinsi</option>';
        for (var i = 0; i < data.length; i++) {
          s += '<option value="' + data[i].province_id + '">' + data[i].province + '</option>';
        }
        $("#provincepenerima").append(s);
      }
    });
  },

  pilihKotaPenerima: function () {
    $.ajax({
      type: "GET",
      url: "/api/kotaRaja",
      contentType: 'application/json',
      dataType: 'json',
      success: function (data) {
        var s = '<option value="-1">Pilih Kota Asal</option>';
        for (var i = 0; i < data.length; i++) {
          s += '<option value="' + data[i].city_id + '">' + data[i].city_name + '</option>';
        }
        $("#city_namepenerima").append(s);
      }
    });
  }
}


var actionDelete = {
  deleteConfirm: function (id) {
    $.ajax({
      url: '/api/barang/' + id,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#form-DropOff').fromJSON(JSON.stringify(res));
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
    if ($('#form-DropOff').parsley().validate()) {
      var dataResult = getJsonForm($("#form-DropOff").serializeArray(), true);

      $.ajax({
        url: '/api/barang/' + dataResult.idBarang,
        method: 'delete',
        success: function () {
          tableBarang.create();
          $('#modal-delete').modal('hide')
        },
        erorrr: function (err) {
          console.log(err);
        }
      });
    }
  }
}
