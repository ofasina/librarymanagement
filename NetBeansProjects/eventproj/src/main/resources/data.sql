-- Insert EVENTS
INSERT INTO events(id, name, eventDescription, category, maxAttendees, date)
VALUES (1, 'FA CUP FINAL', 'ARSNEAL VS MAN-U', 'GAME', '2024-02-15') ON CONFLICT DO NOTHING;