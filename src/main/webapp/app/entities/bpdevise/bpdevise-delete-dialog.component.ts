import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBpdevise } from 'app/shared/model/bpdevise.model';
import { BpdeviseService } from './bpdevise.service';

@Component({
  templateUrl: './bpdevise-delete-dialog.component.html',
})
export class BpdeviseDeleteDialogComponent {
  bpdevise?: IBpdevise;

  constructor(protected bpdeviseService: BpdeviseService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bpdeviseService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bpdeviseListModification');
      this.activeModal.close();
    });
  }
}
