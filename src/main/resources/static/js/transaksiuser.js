var tableTransaksi = {
  create: function () {
    // jika table tersebut datatable, maka clear and dostroy
    if ($.fn.DataTable.isDataTable('#tableTransaksi')) {
      //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
      $('#tableTransaksi').DataTable().clear();
      $('#tableTransaksi').DataTable().destroy();
    }

    $.ajax({
      url: '/api/transaksi/history/',
      method: 'get',
      contentType: 'application/json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#tableTransaksi').DataTable({
            data: res,
            columns: [
              {
                title: "Tangal",
                data: "tanggalTransaksi"
              },
              {
                title: "No. Resi",
                data: "resi"
              },
              {
                title: "Jumlah",
                data: "jumlahBarang"
              },
              {
                title: "Total Berat (gram)",
                data: "beratBarang"
              },
              {
                title: "Pengirim",
                data: "namaPengirim"
              },
              {
                title: "Provinsi Pengirim",
                data: "provinceName"
              },
              {
                title: "Kota Pengirim",
                data: "cityName"
              },
              {
                title: "Kode Pos Pengirim",
                data: "kodePosPengirim"
              },
              {
                title: "Penerima",
                data: "namaPenerima"
              },
              {
                title: "Provinsi Penerima",
                data: "provinceNamePenerima"
              },
              {
                title: "Kota Penerima",
                data: "cityNamePenerima"
              },
              {
                title: "Alamat Penerima",
                data: "alamatPenerima"
              },
              {
                title: "Telp. Penerima",
                data: "telpPenerima"
              },
              {
                title: "Kode Pos Penerima",
                data: "kodePosPenerima"
              },
              {
                title: "Layanan",
                data: "kategoriLayanan"
              },
              {
                title: "Status",
                data: "statusDelivery"
              },
              {
                title: "Kurir",
                data: "namaKurir"
              },
              {
                title: "Penerima Paket",
                data: "penerimaPaket"
              },
              {
                title: "Foto",
                data: null,
                render: function (data, type, row) {
                  return "<img src='/api/transaksi/getFoto/"+data.idTransaksi+"' alt='myImage' style='border-radius: 5px; width: 100px'>"
                }
              },
            ],
            scrollX : true
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

var dropdown = {

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
        $("#province").html(s);
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
        $("#provincepenerima").html(s);
      }
    });
  },

  pilihLayanan: function (asal, tujuan, berat) {
    $.ajax({
      type: "GET",
      url: "/api/cost/" + asal + "/" + tujuan + "/" + berat,
      contentType: 'application/json',
      dataType: 'json',
      success: function (data) {
        var s = '<option value="-1">Pilih Layanan</option>';
        for (var i = 0; i < data.length; i++) {
          s += '<option value="' + data[i].cost[0].value + '" title="'+ data[i].cost[0].etd+'">' + data[i].service + '</option>';
        }
        $("#layanan").html(s);
      }
    });
  },

  pilihKurir: function () {
    $.ajax({
      type: "GET",
      url: "/api/kurir",
      contentType: 'application/json',
      dataType: 'json',
      success: function (data) {
        var s = '<option value="-1">Pilih Kurir</option>';
        for (var i = 0; i < data.length; i++) {
          s += '<option value="' + data[i].idKurir + '">' + data[i].namaKurir + '</option>';
        }
        $("#kurir").html(s);
      }
    });
  },
}


