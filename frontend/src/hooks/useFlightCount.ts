import { useState } from "react";

export interface FlightCountResult {
  count: number;
}

export interface FlightCountError {
  error: string;
  details?: string[];
}

export const useFlightCount = () => {
  const [loading, setLoading] = useState(false);
  const [result, setResult] = useState<FlightCountResult | null>(null);
  const [error, setError] = useState<FlightCountError | null>(null);

  const fetchFlightCount = async (input: string) => {
    setLoading(true);
    setError(null);
    setResult(null);

    try {
      const response = await fetch("http://localhost:8080/api/count", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ input }),
      });

      const data = await response.json();

      if (!response.ok) {
        setError({
          error: data?.error || "Unexpected error",
          details: data?.details || [],
        });
      } else {
        setResult(data);
      }
    } catch (err) {
      console.error(err);
      setError({ error: "Network error" });
    } finally {
      setLoading(false);
    }
  };

  return { loading, result, error, fetchFlightCount };
};
