import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBduAutor } from 'app/shared/model/bdu-autor.model';
import { BduAutorService } from './bdu-autor.service';

@Component({
  templateUrl: './bdu-autor-delete-dialog.component.html',
})
export class BduAutorDeleteDialogComponent {
  bduAutor?: IBduAutor;

  constructor(protected bduAutorService: BduAutorService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bduAutorService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bduAutorListModification');
      this.activeModal.close();
    });
  }
}
