import React, { useState } from "react";
import { useFlightCount } from "../hooks/useFlightCount";

const HomePage: React.FC = () => {
  const [input, setInput] = useState("");
  const { loading, result, error, fetchFlightCount } = useFlightCount();

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    fetchFlightCount(input);
  };

  return (
    <main className="min-h-screen flex items-start justify-center pt-20 bg-gray-50">
      <div className="w-full max-w-xl px-4">
        <h1 className="text-2xl font-bold mb-4 text-center">Flight Finder</h1>

        <form onSubmit={handleSubmit} className="space-y-4">
          <textarea
            className="w-full p-2 border border-gray-300 rounded"
            value={input}
            onChange={(e) => setInput(e.target.value)}
            rows={4}
            placeholder="Enter lowercase characters"
          />
          <button
            type="submit"
            className="w-full px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 disabled:opacity-50"
            disabled={loading}
          >
            {loading ? "Counting..." : 'Count "flight"'}
          </button>
        </form>

        {result && (
          <p data-testid="result" className="mt-4 text-green-700 text-center">
            Found <strong>{result.count}</strong> instance(s) of "flight".
          </p>
        )}

        {error && (
          <div className="mt-4 text-red-600 text-sm">
            <p className="font-semibold">{error.error}</p>
            {error.details && (
              <ul className="list-disc list-inside space-y-1 mt-1">
                {error.details?.map((msg, i) => (
                  <li key={i}>{msg}</li>
                ))}
              </ul>
            )}
          </div>
        )}
      </div>
    </main>
  );
};

export default HomePage;
