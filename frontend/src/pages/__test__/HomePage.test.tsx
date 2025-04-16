import { describe, it, expect, beforeEach, vi } from "vitest";
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";

import HomePage from "../HomePage";

import * as useFlightCountHook from "../../hooks/useFlightCount";

describe("HomePage", () => {
  beforeEach(() => {
    vi.resetAllMocks();
  });

  it("calls fetchFlightCount with user input on submit", async () => {
    const mockFetchFlightCount = vi.fn();

    // Mock the hook return value directly
    vi.spyOn(useFlightCountHook, "useFlightCount").mockReturnValue({
      loading: false,
      result: null,
      error: null,
      fetchFlightCount: mockFetchFlightCount,
    });

    render(<HomePage />);

    const textarea = screen.getByPlaceholderText("Enter lowercase characters");
    await userEvent.type(textarea, "flightflight");

    const button = screen.getByRole("button", { name: /count "flight"/i });
    await userEvent.click(button);

    expect(mockFetchFlightCount).toHaveBeenCalledWith("flightflight");
  });

  it('shows "Counting..." when loading is true', () => {
    vi.spyOn(useFlightCountHook, "useFlightCount").mockReturnValue({
      loading: true,
      result: null,
      error: null,
      fetchFlightCount: vi.fn(),
    });

    render(<HomePage />);
    expect(
      screen.getByRole("button", { name: /counting/i })
    ).toBeInTheDocument();
  });

  it('shows the result when "result.count" exists', () => {
    vi.spyOn(useFlightCountHook, "useFlightCount").mockReturnValue({
      loading: false,
      result: { count: 3 },
      error: null,
      fetchFlightCount: vi.fn(),
    });

    render(<HomePage />);

    expect(screen.getByTestId("result")).toHaveTextContent(
      'Found 3 instance(s) of "flight".'
    );
  });

  it("shows error messages when error is returned", () => {
    vi.spyOn(useFlightCountHook, "useFlightCount").mockReturnValue({
      loading: false,
      result: null,
      error: {
        error: "Validation Error",
        details: ["Input too long", "Only lowercase allowed"],
      },
      fetchFlightCount: vi.fn(),
    });

    render(<HomePage />);
    expect(screen.getByText("Validation Error")).toBeInTheDocument();
    expect(screen.getByText("Input too long")).toBeInTheDocument();
    expect(screen.getByText("Only lowercase allowed")).toBeInTheDocument();
  });
});
