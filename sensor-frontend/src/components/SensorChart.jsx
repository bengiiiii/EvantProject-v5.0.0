// src/components/SensorChart.jsx
import React from "react";
import {
  LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer
} from "recharts";

const SensorChart = ({ data }) => {
  return (
    <div className="p-4 bg-white rounded-lg shadow-md">
      <h2 className="text-xl font-semibold mb-4 text-center">Tüm Ölçümler - Grafik</h2>
      <ResponsiveContainer width="100%" height={300}>
        <LineChart data={data.slice().reverse()} margin={{ top: 10, right: 30, left: 0, bottom: 0 }}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="measurementTime" tick={{ fontSize: 10 }} />
          <YAxis />
          <Tooltip />
          <Legend />
          <Line type="monotone" dataKey="temperature" stroke="#f97316" name="Sıcaklık (°C)" />
          <Line type="monotone" dataKey="humidity" stroke="#0ea5e9" name="Nem (%)" />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
};

export default SensorChart;