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
      // console.log(dataResult)

      $.ajax({
        url: '/api/transaksi',
        method: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(dataResult),
        success: function (res, status, xhr) {
          if (xhr.status == 200 || xhr.status == 201) {
            formDropOff.resetForm();
            $('#modal-sukses').modal("show")

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
      url: '/api/transaksi/' + id,
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

  pilihProvinsi: function () {
    $.ajax({
      type: "GET",
      url: "/api/provinsi",
      contentType: 'application/json',
      dataType: 'json',
      success: function (data) {
        var s = '<option value="-1">Provinsi</option>';
        for (var i = 0; i < data.length; i++) {
          s += '<option value="' + data[i].province_id + '">' + data[i].province + '</option>';
        }
        $("#province").html(s);
      }
    });
  },

  // pilihKota: function () {
  //   $.ajax({
  //     type: "GET",
  //     url: "/api/kotaRaja",
  //     contentType: 'application/json',
  //     dataType: 'json',
  //     success: function (data) {
  //       var s = '<option value="-1">Pilih Kota Asal</option>';
  //       for (var i = 0; i < data.length; i++) {
  //         if (data[i].type == 'Kabupaten') {
  //           s += '<option value="' + data[i].city_id + '">' + data[i].city_name + '</option>';
  //         } else {
  //           s += '<option value="' + data[i].city_id + '">' + data[i].city_name + " (" + data[i].type +")"+ '</option>';
  //         }
  //       }
  //       $("#city_name").html(s);
  //     }
  //   });
  // },

  pilihProvinsiPenerima: function () {
    $.ajax({
      type: "GET",
      url: "/api/provinsi",
      contentType: 'application/json',
      dataType: 'json',
      success: function (data) {
        var s = '<option value="-1">Provinsi</option>';
        for (var i = 0; i < data.length; i++) {
          s += '<option value="' + data[i].province_id + '">' + data[i].province + '</option>';
        }
        $("#provincepenerima").html(s);
      }
    });
  },

  // pilihKotaPenerima: function () {
  //   $.ajax({
  //     type: "GET",
  //     url: "/api/kotaRaja",
  //     contentType: 'application/json',
  //     dataType: 'json',
  //     success: function (data) {
  //       var s = '<option value="-1">Pilih Kota Tujuan</option>';
  //       for (var i = 0; i < data.length; i++) {
  //         if (data[i].type == 'Kabupaten') {
  //           s += '<option value="' + data[i].city_id + '">' + data[i].city_name + '</option>';
  //         } else {
  //           s += '<option value="' + data[i].city_id + '">' + data[i].city_name + " (" + data[i].type +")"+ '</option>';
  //         }
  //       }
  //       $("#city_namepenerima").html(s);
  //     }
  //   });
  // },

  pilihLayanan: function (asal, tujuan, berat) {
    $.ajax({
      type: "GET",
      url: "/api/cost/" + asal + "/" + tujuan + "/" + berat,
      contentType: 'application/json',
      dataType: 'json',
      success: function (data) {
        var s = '<option value="-1">Services</option>';
        for (var i = 0; i < data.length; i++) {
          s += '<option value="' + data[i].cost[0].value + '" title="'+ data[i].cost[0].etd+'">' + data[i].service + '</option>';
        }
        $("#layanan").html(s);
      }
    });
  }
}


var actionDelete = {
  deleteConfirm: function (id) {
    $.ajax({
      url: '/api/transaksi/' + id,
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
        url: '/api/transaksi/' + dataResult.idtransaksi,
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
