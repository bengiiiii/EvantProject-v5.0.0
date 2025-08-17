import React, { useEffect, useState } from "react";
import SensorChart from "./components/SensorChart";

function App() {
  const [latestData, setLatestData] = useState(null);
  const [sensorData, setSensorData] = useState([]);

  // En güncel veriyi çek
  useEffect(() => {
    const fetchLatest = () => {
      fetch("http://localhost:8080/api/evant/latest")
        .then((res) => res.json())
        .then((data) => setLatestData(data));
    };

    fetchLatest();
    const interval = setInterval(fetchLatest, 10000);
    return () => clearInterval(interval);
  }, []);

  // Tüm verileri getir
  useEffect(() => {
    fetch("http://localhost:8080/api/evant/getAll")
      .then((res) => res.json())
      .then((data) => setSensorData(data));
  }, []);

  return (
    <div style={styles.pageContainer}>
      {/* Üst kısım - yan yana iki kutu */}
      <div style={styles.topContainer}>
        {/* Sol kutu - Anlık veri */}
        <div style={styles.card}>
          <h2 style={styles.heading}>📡 Anlık Veri</h2>
          {latestData ? (
            <div>
              <p>
                <strong>🌡️ Sıcaklık:</strong> {latestData.temperature}°C
              </p>
              <p>
                <strong>💧 Nem:</strong> {latestData.humidity}%
              </p>
              <p>
                <strong>🕒 Zaman:</strong> {latestData.measurementTime}
              </p>
              <p>
                <strong>🆔 Sensor ID:</strong> {latestData.sensorId}
              </p>
            </div>
          ) : (
            <p>Yükleniyor...</p>
          )}
        </div>

        {/* Sağ kutu - Tüm kayıtlar */}
        <div style={styles.cardScrollable}>
          <h2 style={styles.heading}>📜 Tüm Kayıtlar</h2>
          <div style={styles.scrollArea}>
            {sensorData.map((item) => (
              <div key={item.id} style={styles.record}>
                <p>
                  <strong>🌡️ Sıcaklık:</strong> {item.temperature}°C
                </p>
                <p>
                  <strong>💧 Nem:</strong> {item.humidity}%
                </p>
                <p>
                  <strong>🕒 Zaman:</strong> {item.measurementTime}
                </p>
                <p>
                  <strong>🆔 Sensor ID:</strong> {item.sensorId}
                </p>
              </div>
            ))}
          </div>
        </div>
      </div>

      {/* Alt kısım - Grafik */}
      <div style={styles.chartContainer}>
        <SensorChart data={sensorData} />
      </div>
    </div>
  );
}

const styles = {
  pageContainer: {
    padding: "20px",
    backgroundColor: "#f0f2f5",
    minHeight: "100vh",
    fontFamily: "Arial, sans-serif",
  },
  topContainer: {
    display: "flex",
    gap: "20px",
  },
  card: {
    flex: 1,
    backgroundColor: "#ffffff",
    padding: "20px",
    borderRadius: "12px",
    boxShadow: "0 4px 12px rgba(0, 0, 0, 0.1)",
  },
  cardScrollable: {
    flex: 1,
    backgroundColor: "#ffffff",
    padding: "20px",
    borderRadius: "12px",
    boxShadow: "0 4px 12px rgba(0, 0, 0, 0.1)",
    maxHeight: "500px",
    overflowY: "auto",
    display: "flex",
    flexDirection: "column",
  },
  scrollArea: {
    flex: 1,
  },
  heading: {
    color: "#333",
    marginBottom: "15px",
    borderBottom: "2px solid #007bff",
    paddingBottom: "5px",
  },
  record: {
    padding: "10px",
    borderBottom: "1px solid #ddd",
    marginBottom: "10px",
    borderRadius: "8px",
    backgroundColor: "#f9f9f9",
  },
  chartContainer: {
    backgroundColor: "#ffffff",
    padding: "20px",
    borderRadius: "12px",
    boxShadow: "0 4px 12px rgba(0, 0, 0, 0.1)",
    marginTop: "30px",
  },
};

export default App;