import { renderHook, act } from "@testing-library/react";
import { useFlightCount } from "../useFlightCount";

describe("useFlightCount", () => {
  afterEach(() => {
    vi.restoreAllMocks();
  });

  it("returns result on success", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn(async () => ({
        ok: true,
        json: async () => ({ count: 2 }),
      }))
    );

    const { result } = renderHook(() => useFlightCount());

    await act(async () => {
      await result.current.fetchFlightCount("flightflight");
    });

    expect(result.current.result).toEqual({ count: 2 });
    expect(result.current.error).toBeNull();
  });

  it("handles 400 with details", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn().mockResolvedValueOnce({
        ok: false,
        json: async () => ({
          error: "Validation failed",
          details: ["input: too long"],
        }),
      })
    );

    const { result } = renderHook(() => useFlightCount());

    await act(async () => {
      await result.current.fetchFlightCount("a".repeat(101));
    });

    expect(result.current.error).toEqual({
      error: "Validation failed",
      details: ["input: too long"],
    });
  });
});
